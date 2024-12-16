package com.zachholt.WeatherAPI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.weather")
public class WeatherClientConfig {
    private String host;
    private String weatherPath;
    private String apiKey;

    public String getHost() {
        return host;
    }

    public WeatherClientConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getWeatherPath() {
        return weatherPath;
    }

    public WeatherClientConfig setWeatherPath(String weatherPath) {
        this.weatherPath = weatherPath;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public WeatherClientConfig setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}
