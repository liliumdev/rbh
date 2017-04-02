package controllers.forms;

import play.data.validation.Constraints;

public class CompleteReservationForm {
    @Constraints.Required
    private String time;

    @Constraints.Required
    private Long restaurantId;

    @Constraints.Required
    private Long tableId;

    @Constraints.Required
    private Integer persons;

    private String request;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public String getRequest() { return request; }

    public void setRequest(String request) { this.request = request; }
}