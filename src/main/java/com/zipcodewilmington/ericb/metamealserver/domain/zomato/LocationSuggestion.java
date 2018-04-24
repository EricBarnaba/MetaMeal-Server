package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

public class LocationSuggestion {

    private int id;
    private String name;
    private int country_id;
    private String country_name;
    private String country_flag_url;
    private int should_experiment_with;
    private int discovery_enabled;
    private int has_new_ad_format;
    private int is_state;
    private int state_id;
    private String state_name;
    private String state_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_flag_url() {
        return country_flag_url;
    }

    public void setCountry_flag_url(String country_flag_url) {
        this.country_flag_url = country_flag_url;
    }

    public int getShould_experiment_with() {
        return should_experiment_with;
    }

    public void setShould_experiment_with(int should_experiment_with) {
        this.should_experiment_with = should_experiment_with;
    }

    public int getDiscovery_enabled() {
        return discovery_enabled;
    }

    public void setDiscovery_enabled(int discovery_enabled) {
        this.discovery_enabled = discovery_enabled;
    }

    public int getHas_new_ad_format() {
        return has_new_ad_format;
    }

    public void setHas_new_ad_format(int has_new_ad_format) {
        this.has_new_ad_format = has_new_ad_format;
    }

    public int getIs_state() {
        return is_state;
    }

    public void setIs_state(int is_state) {
        this.is_state = is_state;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }
}
