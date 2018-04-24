package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import java.util.List;

public class SuggestedLocation {

    private List<LocationSuggestion> location_suggestions;
    private String status;
    private int has_more;
    private int has_total;

    public List<LocationSuggestion> getLocation_suggestions() {
        return location_suggestions;
    }

    public void setLocation_suggestions(List<LocationSuggestion> location_suggestions) {
        this.location_suggestions = location_suggestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHas_more() {
        return has_more;
    }

    public void setHas_more(int has_more) {
        this.has_more = has_more;
    }

    public int getHas_total() {
        return has_total;
    }

    public void setHas_total(int has_total) {
        this.has_total = has_total;
    }
}
