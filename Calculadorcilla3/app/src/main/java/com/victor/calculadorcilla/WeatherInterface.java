package com.victor.calculadorcilla;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victor on 04/02/2017.
 */

public interface WeatherInterface {
    @GET("data/2.5/weather/")
    Call<WeatherMessage> getWeather(@Query("lat") String lati, @Query("lon") String longi, @Query("APPID") String appid);
}
