package com.zachholt.WeatherAPI.models;

import com.zachholt.WeatherAPI.models.external.Time;
import com.zachholt.WeatherAPI.models.external.WeatherInfo;

import java.util.UUID;

public class Weather {
    private UUID id;
    private String name;
    private String region;
    private String country;
    private double latitude;
    private double longitude;
    private Time time;
    private WeatherInfo weather;

    public UUID getId() {
        return id;
    }

    public Weather setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Weather setName(String name) {
        this.name = name;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Weather setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Weather setCountry(String country) {
        this.country = country;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Weather setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Weather setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public Weather setTime(Time time) {
        this.time = time;
        return this;
    }

    public WeatherInfo getWeather() {
        return weather;
    }

    public Weather setWeather(WeatherInfo weather) {
        this.weather = weather;
        return this;
    }
}