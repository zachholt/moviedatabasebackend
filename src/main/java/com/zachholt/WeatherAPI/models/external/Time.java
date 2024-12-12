package com.zachholt.WeatherAPI.models.external;

public class Time {
    private static final boolean IS_CURRENT = true;
    
    private final boolean current = IS_CURRENT;
    private String lastUpdatedDateTime;

    public boolean isCurrent() {
        return current;
    }

    public String getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }
} 