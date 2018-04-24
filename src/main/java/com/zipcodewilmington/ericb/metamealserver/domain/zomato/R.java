package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class R {

    @Id
    @JsonProperty(value = "res_id")
    private Long ResId;

    public Long getResId() {
        return ResId;
    }

    public void setResId(Long resId) {
        ResId = resId;
    }
}
