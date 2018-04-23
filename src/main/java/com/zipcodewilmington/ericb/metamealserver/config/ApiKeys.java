package com.zipcodewilmington.ericb.metamealserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class ApiKeys {

    @Value("${yelpKey}")
    private String yelpKey;

    @Value("${zomatoKey}")
    private String zomatoKey;

    public String getYelpKey() {
        return yelpKey;
    }

    public String getZomatoKey() {
        return zomatoKey;
    }

}
