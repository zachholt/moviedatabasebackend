package com.zachholt.WeatherAPI.models.external;

public class WeatherInfo {
    private static final String TEMP_UNIT = "fahrenheit";
    
    private double temp;
    private double feelsLikeTemp;
    private final String tempUnit = TEMP_UNIT;
    private int humidity;
    private Wind wind;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(double feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
} 