package com.zachholt.MovieDatabaseAPI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.movie")
public class MovieDatabaseClientConfig {
    private String host;
    private String moviePath;
    private String apiKey;

    public String getHost() {
        return host;
    }

    public MovieDatabaseClientConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getMoviePath() {
        return moviePath;
    }

    public MovieDatabaseClientConfig setMoviePath(String moviePath) {
        this.moviePath = moviePath;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public MovieDatabaseClientConfig setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}
