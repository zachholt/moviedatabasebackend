package com.zachholt.WeatherAPI.clients;

import com.zachholt.WeatherAPI.models.external.WeatherRapid;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {
    private final RestTemplate restTemplate;

    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherRapid getWeather(String zipcode) {
        return restTemplate.getForObject(
            "https://weatherapi-com.p.rapidapi.com/current.json?q={zipcode}",
            WeatherRapid.class,
            zipcode
        );
    }
}
