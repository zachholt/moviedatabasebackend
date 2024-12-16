package com.zachholt.WeatherAPI.clients;

import com.zachholt.WeatherAPI.config.WeatherClientConfig;
import com.zachholt.WeatherAPI.models.external.WeatherRapid;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class WeatherClientV2 {
    private final WebClient webClient;
    private final WeatherClientConfig weatherClientConfig;

    public WeatherClientV2(WebClient.Builder webClientBuilder, WeatherClientConfig weatherClientConfig) {
        this.weatherClientConfig = weatherClientConfig;
        this.webClient = webClientBuilder
            .baseUrl(weatherClientConfig.getHost())
            .defaultHeaders(httpHeaders())
            .build();
    }

    private Consumer<HttpHeaders> httpHeaders() {
        return httpHeaders -> {
            httpHeaders.set("X-RapidAPI-Key", weatherClientConfig.getApiKey());
        };
    }

    public Mono<WeatherRapid> getWeatherV2(String zipcode) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(weatherClientConfig.getWeatherPath())
                .queryParam("q", zipcode)
                .build())
            .retrieve()
            .bodyToMono(WeatherRapid.class);
    }
}
