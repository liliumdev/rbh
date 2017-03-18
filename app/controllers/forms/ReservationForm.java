package controllers.forms;

import play.data.validation.Constraints;

public class ReservationForm {

    @Constraints.Required
    private String wishedTime;

    @Constraints.Required
    private Long restaurantId;

    @Constraints.Required
    @Constraints.Min(2)
    private Integer persons;

    public String getWishedTime() {
        return wishedTime;
    }

    public void setWishedTime(String wishedTime) {
        this.wishedTime = wishedTime;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }
}