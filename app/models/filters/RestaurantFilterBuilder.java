package models.filters;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgtPepper on 01.03.2017..
 */
public class RestaurantFilterBuilder extends BaseFilterBuilder<RestaurantFilterBuilder> {
    private String name;
    private List<String> categories;
    private Integer rating;
    private Integer pricing;
    private String query;

    public RestaurantFilterBuilder() {
        setName("");
        setCategories(new ArrayList<>());
        setRating(null);
        setPricing(null);
        setQuery(null);
    }

    @Override
    public Criteria addConditions(Criteria rootCriteria) {
        if(StringUtils.isNotBlank(getName())) {
            rootCriteria = rootCriteria.add(Restrictions.ilike("name", getName(), MatchMode.ANYWHERE));
        }

        if(getCategories() != null && getCategories().size() > 0) {
            rootCriteria.createAlias("categories", "category");
            rootCriteria.add(Restrictions.in("category.name", getCategories()));
        }

        if(getQuery() != null && StringUtils.isNotBlank(getQuery())) {
            rootCriteria.createAlias("city", "city");

            rootCriteria.add(Restrictions.or(
                    Restrictions.ilike("city.name", getQuery(), MatchMode.ANYWHERE),
                    Restrictions.ilike("name", getQuery(), MatchMode.ANYWHERE)
            ));
        }

        if(getRating() != null && getRating() > 0) {
            rootCriteria.add(Restrictions.ge("reviewRating", ((double)(rating) - 0.5)));
            rootCriteria.add(Restrictions.lt("reviewRating", ((double)(rating) + 0.5)));
        }

        if(getPricing() != null && getPricing() > 0) {
            rootCriteria.add(Restrictions.eq("pricing", pricing));
        }

        return super.addConditions(rootCriteria);
    }

    public String getName() {
        return name;
    }

    public RestaurantFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public RestaurantFilterBuilder setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public RestaurantFilterBuilder addCategory(String category) {
        this.categories.add(category);
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public RestaurantFilterBuilder setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public Integer getPricing() {
        return pricing;
    }

    public RestaurantFilterBuilder setPricing(Integer pricing) {
        this.pricing = pricing;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public RestaurantFilterBuilder setQuery(String query) {
        this.query = query;
        return this;
    }
}
