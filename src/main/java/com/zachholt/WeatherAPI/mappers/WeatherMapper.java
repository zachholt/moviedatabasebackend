package com.zachholt.WeatherAPI.mappers;

import com.zachholt.WeatherAPI.models.Weather;
import com.zachholt.WeatherAPI.models.external.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "name", source = "location.name")
    @Mapping(target = "region", source = "location.region")
    @Mapping(target = "country", source = "location.country")
    @Mapping(target = "latitude", source = "location.lat")
    @Mapping(target = "longitude", source = "location.lon")
    @Mapping(target = "time.lastUpdatedDateTime", source = "current.last_updated")
    @Mapping(target = "weather.temp", source = "current.temp_f")
    @Mapping(target = "weather.feelsLikeTemp", source = "current.feelslike_f")
    @Mapping(target = "weather.wind.gustSpeed", source = "current.gust_mph")
    @Mapping(target = "weather.wind.windSpeed", source = "current.wind_mph")
    @Mapping(target = "weather.wind.windDirection", source = "current.wind_dir")
    @Mapping(target = "weather.wind.gustSpeedTimesWindSpeed", expression = "java(current.getGust_mph() * current.getWind_mph())")
    Weather mapRapidToWeather(WeatherRapid weatherRapid);

}
