package services;

import models.Account;
import models.DiningTable;
import models.Reservation;
import models.Restaurant;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import repositories.ReservationRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ReservationService extends BaseService<Reservation, ReservationRepository> {
    private DiningTableService diningTableService;
    private AccountService accountService;
    private RestaurantService restaurantService;

    @Inject
    public void setDiningTableService(DiningTableService diningTableService) {
        this.diningTableService = diningTableService;
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Creates a reservation in the restaurant, at a given table for the given date and time, linked to the account
     * that has the given email.
     *
     * @param restaurantId
     * @param time         Time string in format yyyy-MM-dd HH:mm
     * @param tableId      ID of the table the reservation is for
     * @param persons      Number of persons for this reservation
     * @param email        Email of the account this reservations is made for
     * @return Model of the newly created reservation
     * @throws ServiceException
     */
    public Reservation create(Long restaurantId, String time, Long tableId, Integer persons, String email) throws ServiceException {
        try {
            Restaurant restaurant = restaurantService.get(restaurantId);
            if(restaurant == null) {
                return null;
            }

            DiningTable diningTable = diningTableService.get(tableId);
            if(diningTable == null) {
                return null;
            }

            Account account = accountService.getByEmail(email);
            if(account == null) {
                return null;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime reservationTime = LocalDateTime.parse(time, formatter).minusHours(1); // very hacky because of timezones
            Instant instant = reservationTime.toInstant(ZoneOffset.UTC); // we're converting everything to UTC
            Date finalReservationTime = Date.from(instant);

            return create(Reservation.createReservation(diningTable, account, new Date(), finalReservationTime, persons));
        } catch(Exception e) {
            throw new ServiceException("ReservationService couldn't find reservations.", e);
        }
    }

    /**
     * All reservations made by an account
     *
     * @param email Email of the account
     * @return A list of all reservations by this account, or null if the account is non-existent
     * @throws ServiceException
     */
    public List<Reservation.MyReservationDto> getReservationsByEmail(String email) throws ServiceException {
        try {
            Account account = accountService.getByEmail(email);
            if(account == null) {
                return null;
            }

            String sql = new StringBuilder()
                    .append("SELECT reservation.id AS \"id\", reservation.for_time AS \"forTime\", reservation.persons AS \"persons\", restaurant.name AS \"name\" ")
                    .append("FROM reservation, account, diningtable, restaurant ")
                    .append("WHERE reservation.account_id=account.id AND  ")
                    .append("	  account.email = (?1) AND ")
                    .append("      reservation.table_id=diningtable.id AND ")
                    .append("      diningtable.restaurant_id=restaurant.id ")
                    .append("ORDER BY \"forTime\" ASC ")
                    .toString();

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setParameter(1, email);
            query.setResultTransformer(Transformers.aliasToBean(Reservation.MyReservationDto.class));


            return (List<Reservation.MyReservationDto>) query.getResultList();
        } catch(Exception e) {
            throw new ServiceException("ReservationService couldn't find reservations.", e);
        }
    }

    /**
     * Deletes a reservation made by an account with the given email.
     *
     * @param id    ID of the reservation to delete
     * @param email Email of the account
     * @throws ServiceException If the account didn't make this reservation
     */
    public void deleteReservation(Long id, String email) throws ServiceException {
        try {
            Account account = accountService.getByEmail(email);
            if(account == null) {
                throw new ServiceException("ReservationService: this account can't delete this reservation.");
            }

            delete(id);
        } catch(Exception e) {
            throw new ServiceException("ReservationService: this account can't delete this reservation.", e);
        }
    }

    /**
     * Indicates whether the table of a given restaurant is available for a reservation, at the given time
     *
     * @param time         Time string in format yyyy-MM-dd HH:mm
     * @param restaurantId
     * @param tableId
     * @return
     * @throws ServiceException
     */
    public Boolean isReservationAvailable(String time, Long restaurantId, Long tableId) throws ServiceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime wishedTime = LocalDateTime.parse(time, formatter);
            LocalDateTime now = LocalDateTime.now();

            // Wished time is in the past or not in an appropriate interval
            if(wishedTime.isBefore(now) || (wishedTime.getMinute() != 0 && wishedTime.getMinute() != 30)) {
                return false;
            }

            String sql = new StringBuilder()
                    .append("WITH suggested_times AS ( ")
                    .append("    SELECT (?1)::::timestamp wished_time ")
                    .append(") ")
                    .append("SELECT (COALESCE(SUM(diningtable.amount) - SUM(reserved_times.tables_taken), 0) > 0) free_tables ")
                    .append("       FROM suggested_times, ")
                    .append("       ( ")
                    .append("           SELECT COUNT(reservation.for_time) tables_taken, diningtable.id, MIN(diningtable.persons), suggested_times.wished_time  ")
                    .append("           FROM suggested_times ")
                    .append("           INNER JOIN restaurant ON restaurant.id=?2 ")
                    .append("           INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id AND diningtable.id = ?3 ")
                    .append("           LEFT OUTER JOIN reservation ON diningtable.id=reservation.table_id AND reservation.for_time=suggested_times.wished_time ")
                    .append("           GROUP BY(suggested_times.wished_time, diningtable.id) ")
                    .append("       ) AS reserved_times ")
                    .append("INNER JOIN restaurant ON restaurant.id=?2 ")
                    .append("INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ")
                    .append("WHERE  ")
                    .append("      diningtable.id=?3 AND ")
                    .append("      reserved_times.wished_time = suggested_times.wished_time ")
                    .toString();

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setParameter(1, time);
            query.setParameter(2, restaurantId);
            query.setParameter(3, tableId);

            return (Boolean) query.getSingleResult();
        } catch(Exception e) {
            throw new ServiceException("ReservationService couldn't see if a reservation is available.", e);
        }
    }

    /**
     * Generates a list of reservation suggestions in 30-minute intervals, between given times in the restaurant.
     * All suggestions are linked to the best available table that can hold the specified number of people.
     *
     * @param timeFrom     Start of the time interval we're interested in
     * @param timeTo       End of the time interval we're interested in
     * @param restaurantId
     * @param persons      Number of people that we want to reserve a table for
     * @return A list of reservation suggestions
     * @throws ServiceException
     */
    public List<Reservation.ReservationSuggestionDto>
    getReservationSuggestions(String timeFrom, String timeTo, Long restaurantId, Integer persons) throws ServiceException {
        try {
            String sql = new StringBuilder()
                    .append("WITH suggested_times AS ( ")
                    .append("    SELECT sugg_time::::timestamp ")
                    .append("    FROM generate_series( (?1)::::timestamp, (?2)::::timestamp, '30 minutes'::::interval) sugg_time ")
                    .append(") ")
                    .append("SELECT DISTINCT ON(sugg_time) persons AS \"persons\", CAST(free_tables AS INTEGER) AS \"freeTables\", id AS \"tableId\", sugg_time AS \"suggestedTime\" FROM ")
                    .append("( ")
                    .append("    SELECT SUM(diningtable.amount) - SUM(reserved_times.tables_taken) free_tables,  ")
                    .append("           diningtable.id,  ")
                    .append("           diningtable.persons,         ")
                    .append("           suggested_times.sugg_time ")
                    .append("           FROM suggested_times, ")
                    .append("           ( ")
                    .append("                SELECT COUNT(reservation.for_time) tables_taken, diningtable.id, MIN(diningtable.persons), suggested_times.sugg_time  ")
                    .append("                FROM suggested_times ")
                    .append("                INNER JOIN restaurant ON restaurant.id=(?3) ")
                    .append("                INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ")
                    .append("                LEFT OUTER JOIN reservation ON diningtable.id=reservation.table_id AND reservation.for_time=suggested_times.sugg_time ")
                    .append("                GROUP BY(suggested_times.sugg_time, diningtable.id) ")
                    .append("                HAVING MIN(diningtable.persons) >= (?4) ")
                    .append("           ) AS reserved_times ")
                    .append("    INNER JOIN restaurant ON restaurant.id=(?3) ")
                    .append("    INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ")
                    .append("    WHERE reserved_times.id=diningtable.id AND ")
                    .append("          reserved_times.sugg_time = suggested_times.sugg_time ")
                    .append("    GROUP BY(suggested_times.sugg_time, diningtable.id, diningtable.persons) ")
                    .append("    HAVING SUM(diningtable.amount) - SUM(reserved_times.tables_taken) > 0 ")
                    .append(") suggestions ")
                    .append("ORDER BY \"suggestedTime\" ASC ")
                    .toString();

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setParameter(1, timeFrom);
            query.setParameter(2, timeTo);
            query.setParameter(3, restaurantId);
            query.setParameter(4, persons);
            query.setResultTransformer(Transformers.aliasToBean(Reservation.ReservationSuggestionDto.class));

            return (List<Reservation.ReservationSuggestionDto>) query.getResultList();
        } catch(Exception e) {
            throw new ServiceException("ReservationService couldn't find reservation suggestions.", e);
        }
    }


}
