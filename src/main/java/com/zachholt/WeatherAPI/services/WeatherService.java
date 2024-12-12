package com.zachholt.WeatherAPI.services;

import com.zachholt.WeatherAPI.clients.WeatherClient;
import com.zachholt.WeatherAPI.mappers.WeatherMapper;
import com.zachholt.WeatherAPI.models.Weather;
import com.zachholt.WeatherAPI.models.external.WeatherRapid;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;

    public WeatherService(WeatherClient weatherClient, WeatherMapper weatherMapper) {
        this.weatherClient = weatherClient;
        this.weatherMapper = weatherMapper;
    }

    public Weather getWeather(String zipcode) {
        WeatherRapid weatherRapid = weatherClient.getWeather(zipcode);
        return weatherMapper.mapRapidToWeather(weatherRapid);
    }
} 