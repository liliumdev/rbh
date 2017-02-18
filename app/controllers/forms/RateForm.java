package controllers.forms;

import play.data.validation.Constraints;

/**
 * Created by Lilium on 28.1.2017.
 */
public class RateForm {
    @Constraints.Required
    @Constraints.Min(1)
    @Constraints.Max(5)
    private Integer rating;

    private String description;


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}