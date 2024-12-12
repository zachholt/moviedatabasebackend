package com.zachholt.WeatherAPI.models.external;

import com.zachholt.WeatherAPI.models.external.WeatherRapid;

public class WeatherResponse {
    private WeatherRapid data;

    public WeatherRapid getData() {
        return data;
    }

    public WeatherResponse setData(WeatherRapid data) {
        this.data = data;
        return this;
    }
}