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
    @Headers({ "Accept: application/json" })
    @GET("/weather")
    Call<WeatherMessage> getWeather(@Query("lat") double lati, @Query("lon") double longi, @Query("APPID") String appid);
}
