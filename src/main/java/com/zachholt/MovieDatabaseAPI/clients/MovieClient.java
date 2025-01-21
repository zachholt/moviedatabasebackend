package com.zachholt.MovieDatabaseAPI.clients;

import com.zachholt.MovieDatabaseAPI.config.MovieDatabaseClientConfig;
import com.zachholt.MovieDatabaseAPI.models.movieExternal.MovieDatabaseItem;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class MovieClient {
    private final WebClient webClient;
    private final MovieDatabaseClientConfig movieDatabaseClientConfig;

    public MovieClient(WebClient.Builder webClientBuilder, MovieDatabaseClientConfig movieDatabaseClientConfig) {
        this.movieDatabaseClientConfig = movieDatabaseClientConfig;
        this.webClient = webClientBuilder
                .baseUrl(movieDatabaseClientConfig.getHost())
                .defaultHeaders(httpHeaders())
                .build();
    }

    private Consumer<HttpHeaders> httpHeaders() {
        return httpHeaders -> {
            httpHeaders.set("api_key", movieDatabaseClientConfig.getApiKey());
        };
    }

    public Mono<MovieDatabaseItem> searchMovie(String movieTitle) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(movieDatabaseClientConfig.getMoviePath())
                        .queryParam("query", movieTitle)
                        .queryParam("api_key", movieDatabaseClientConfig.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(MovieDatabaseItem.class);
    }
}
