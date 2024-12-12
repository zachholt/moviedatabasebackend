package com.zachholt.WeatherAPI.models.external;

public class WeatherRapid {
    private Location location;
    private Current current;

    public Location getLocation() {
        return location;
    }

    public WeatherRapid setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Current getCurrent() {
        return current;
    }

    public WeatherRapid setCurrent(Current current) {
        this.current = current;
        return this;
    }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;

        public String getName() {
            return name;
        }

        public Location setName(String name) {
            this.name = name;
            return this;
        }

        public String getRegion() {
            return region;
        }

        public Location setRegion(String region) {
            this.region = region;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Location setCountry(String country) {
            this.country = country;
            return this;
        }

        public double getLat() {
            return lat;
        }

        public Location setLat(double lat) {
            this.lat = lat;
            return this;
        }

        public double getLon() {
            return lon;
        }

        public Location setLon(double lon) {
            this.lon = lon;
            return this;
        }
    }

    public static class Current {
        private String last_updated;
        private double temp_f;
        private double feelslike_f;
        private int humidity;
        private double wind_mph;
        private String wind_dir;
        private double gust_mph;

        public String getLast_updated() {
            return last_updated;
        }

        public Current setLast_updated(String last_updated) {
            this.last_updated = last_updated;
            return this;
        }

        public double getTemp_f() {
            return temp_f;
        }

        public Current setTemp_f(double temp_f) {
            this.temp_f = temp_f;
            return this;
        }

        public double getFeelslike_f() {
            return feelslike_f;
        }

        public Current setFeelslike_f(double feelslike_f) {
            this.feelslike_f = feelslike_f;
            return this;
        }

        public int getHumidity() {
            return humidity;
        }

        public Current setHumidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public double getWind_mph() {
            return wind_mph;
        }

        public Current setWind_mph(double wind_mph) {
            this.wind_mph = wind_mph;
            return this;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public Current setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
            return this;
        }

        public double getGust_mph() {
            return gust_mph;
        }

        public Current setGust_mph(double gust_mph) {
            this.gust_mph = gust_mph;
            return this;
        }
    }
}