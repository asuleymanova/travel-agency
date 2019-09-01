package tech.kibrit.travelagency.model;

import tech.kibrit.travelagency.controller.TourDetailController;

import java.util.Date;
import java.util.List;

public class MultiSelect {

    List<Long>  ids;
    Boolean checkIds;
    Double minPrice;
    Double maxPrice;
    Date startDate;
    Date endDate;

    public MultiSelect() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Boolean getCheckIds() {
        return ids != null && !ids.isEmpty() ? true : null;
       // return checkIds;
    }

    public void setCheckIds(Boolean checkIds) {
        this.checkIds = checkIds;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
