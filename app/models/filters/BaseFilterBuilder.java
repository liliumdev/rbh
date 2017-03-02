package models.filters;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 * Created by sgtPepper on 01.03.2017..
 */
public abstract class BaseFilterBuilder<B extends BaseFilterBuilder> {
    private Integer pageSize;
    private Integer pageNumber;
    private String sortKey;
    private Boolean sortAsc;

    public BaseFilterBuilder() {
        setPageSize(0);
        setPageNumber(0);
        setSortKey("");
        setSortAsc(false);
    }

    protected Criteria addConditions(Criteria rootCriteria) {
        if (getPageSize() != null && getPageSize() != 0) {
            rootCriteria = this.addLimitAndOffset(rootCriteria);
        }

        if(StringUtils.isNotBlank(sortKey)) {
            rootCriteria = this.addSort(rootCriteria);
        }

        return rootCriteria;
    }

    protected Criteria addLimitAndOffset(Criteria rootCriteria) {
        if(getPageNumber() == null) {
            setPageNumber(0);
        }
        rootCriteria.setFirstResult(getPageNumber() * getPageSize());
        rootCriteria.setMaxResults(getPageSize());

        return rootCriteria;
    }

    protected Criteria addSort(Criteria rootCriteria) {
        if(sortAsc) {
            rootCriteria.addOrder(Order.asc(sortKey));
        } else {
            rootCriteria.addOrder(Order.desc(sortKey));
        }

        return rootCriteria;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public B setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return (B)this;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public B setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return (B)this;
    }

    public String getSortKey() {
        return sortKey;
    }

    public B setSortKey(String sortKey) {
        this.sortKey = sortKey;
        return (B)this;
    }

    public Boolean getSortAsc() {
        return sortAsc;
    }

    public B setSortAsc(Boolean sortAsc) {
        this.sortAsc = sortAsc;
        return (B)this;
    }
}
