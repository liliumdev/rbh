package services;

import models.Account;
import models.DiningTable;
import models.Reservation;
import models.Restaurant;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import repositories.ReservationRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Lilium on 14.1.2017.
 */

public class ReservationService extends BaseService<Reservation, ReservationRepository> {
    private DiningTableService diningTableService;
    private AccountService accountService;
    private RestaurantService restaurantService;

    @Inject
    public void setDiningTableService(DiningTableService diningTableService) { this.diningTableService = diningTableService; }

    @Inject
    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @Inject
    public void setRestaurantService(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }

    public Boolean isReservationAvailable(String time, Long restaurantId, Long tableId) throws ServiceException {
        try {
            String sql = "";
            sql += "WITH suggested_times AS ( ";
            sql += "    SELECT (?1)::::timestamp wished_time ";
            sql += ") ";
            sql += "SELECT (COALESCE(SUM(diningtable.amount) - SUM(reserved_times.tables_taken), 0) > 0) free_tables ";
            sql += "       FROM suggested_times, ";
            sql += "       ( ";
            sql += "           SELECT COUNT(reservation.for_time) tables_taken, diningtable.id, MIN(diningtable.persons), suggested_times.wished_time  ";
            sql += "           FROM suggested_times ";
            sql += "           INNER JOIN restaurant ON restaurant.id=?2 ";
            sql += "           INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id AND diningtable.id = ?3 ";
            sql += "           LEFT OUTER JOIN reservation ON diningtable.id=reservation.table_id AND reservation.for_time=suggested_times.wished_time ";
            sql += "           GROUP BY(suggested_times.wished_time, diningtable.id) ";
            sql += "       ) AS reserved_times ";
            sql += "INNER JOIN restaurant ON restaurant.id=?2 ";
            sql += "INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ";
            sql += "WHERE  ";
            sql += "      diningtable.id=?3 AND ";
            sql += "      reserved_times.wished_time = suggested_times.wished_time ";

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setParameter(1, time);
            query.setParameter(2, restaurantId);
            query.setParameter(3, tableId);

            return (Boolean)query.getSingleResult();
        } catch (Exception e) {
            throw new ServiceException("ReservationService couldn't see if a reservation is available.", e);
        }
    }

    public List<Reservation.ReservationSuggestionDto>
            getReservationSuggestions(String timeFrom, String timeTo, Long restaurantId, Integer persons) throws ServiceException {
        try {
            String sql = "";
            sql += "WITH suggested_times AS ( ";
            sql += "    SELECT sugg_time::::timestamp ";
            sql += "    FROM generate_series( (?1)::::timestamp, (?2)::::timestamp, '30 minutes'::::interval) sugg_time ";
            sql += ") ";
            sql += "SELECT DISTINCT ON(sugg_time) persons AS \"persons\", CAST(free_tables AS INTEGER) AS \"freeTables\", id AS \"tableId\", sugg_time AS \"suggestedTime\" FROM ";
            sql += "( ";
            sql += "    SELECT SUM(diningtable.amount) - SUM(reserved_times.tables_taken) free_tables,  ";
            sql += "           diningtable.id,  ";
            sql += "           diningtable.persons,         ";
            sql += "           suggested_times.sugg_time ";
            sql += "           FROM suggested_times, ";
            sql += "           ( ";
            sql += "                SELECT COUNT(reservation.for_time) tables_taken, diningtable.id, MIN(diningtable.persons), suggested_times.sugg_time  ";
            sql += "                FROM suggested_times ";
            sql += "                INNER JOIN restaurant ON restaurant.id=(?3) ";
            sql += "                INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ";
            sql += "                LEFT OUTER JOIN reservation ON diningtable.id=reservation.table_id AND reservation.for_time=suggested_times.sugg_time ";
            sql += "                GROUP BY(suggested_times.sugg_time, diningtable.id) ";
            sql += "                HAVING MIN(diningtable.persons) >= (?4) ";
            sql += "           ) AS reserved_times ";
            sql += "    INNER JOIN restaurant ON restaurant.id=(?3) ";
            sql += "    INNER JOIN diningtable ON restaurant.id=diningtable.restaurant_id ";
            sql += "    WHERE reserved_times.id=diningtable.id AND ";
            sql += "          reserved_times.sugg_time = suggested_times.sugg_time ";
            sql += "    GROUP BY(suggested_times.sugg_time, diningtable.id, diningtable.persons) ";
            sql += "    HAVING SUM(diningtable.amount) - SUM(reserved_times.tables_taken) > 0 ";
            sql += ") suggestions ";
            sql += "ORDER BY \"suggestedTime\" ASC ";

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setParameter(1, timeFrom);
            query.setParameter(2, timeTo);
            query.setParameter(3, restaurantId);
            query.setParameter(4, persons);
            query.setResultTransformer(Transformers.aliasToBean(Reservation.ReservationSuggestionDto.class));


            return (List<Reservation.ReservationSuggestionDto>)query.getResultList();
        } catch (Exception e) {
            throw new ServiceException("ReservationService couldn't find reservation suggestions.", e);
        }
    }
}
