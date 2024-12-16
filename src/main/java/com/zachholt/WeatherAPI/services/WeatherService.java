package com.zachholt.WeatherAPI.services;

import com.zachholt.WeatherAPI.clients.WeatherClient;
import com.zachholt.WeatherAPI.clients.WeatherClientV2;
import com.zachholt.WeatherAPI.mappers.WeatherMapper;
import com.zachholt.WeatherAPI.models.Weather;
import com.zachholt.WeatherAPI.models.external.WeatherRapid;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherClientV2 weatherClientV2;
    private final WeatherMapper weatherMapper;

    public WeatherService(WeatherClient weatherClient, WeatherClientV2 weatherClientV2, WeatherMapper weatherMapper) {
        this.weatherClient = weatherClient;
        this.weatherClientV2 = weatherClientV2;
        this.weatherMapper = weatherMapper;
    }

    public Weather getWeather(String zipcode) {
        WeatherRapid weatherRapid = weatherClient.getWeather(zipcode);
        return weatherMapper.mapRapidToWeather(weatherRapid);
    }

    public Weather getWeatherWebClient(String zipcode) {
        WeatherRapid weatherRapid = weatherClientV2.getWeatherV2(zipcode).block();
        return weatherMapper.mapRapidToWeather(weatherRapid);
    }
} 