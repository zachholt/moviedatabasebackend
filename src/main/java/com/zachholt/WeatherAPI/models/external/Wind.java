package com.zachholt.WeatherAPI.models.external;

public class Wind {
    private static final String WIND_SPEED_UNIT = "mph";
    
    private double windSpeed;
    private final String windSpeedUnit = WIND_SPEED_UNIT;
    private String windDirection;
    private double gustSpeed;
    private double gustSpeedTimesWindSpeed;


    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getGustSpeed() {
        return gustSpeed;
    }

    public void setGustSpeed(double gustSpeed) {
        this.gustSpeed = gustSpeed;
    }

    public double getGustSpeedTimesWindSpeed() {
        return gustSpeedTimesWindSpeed;
    }

    public Wind setGustSpeedTimesWindSpeed(double gustSpeedTimesWindSpeed) {
        this.gustSpeedTimesWindSpeed = gustSpeedTimesWindSpeed;
        return this;
    }
}