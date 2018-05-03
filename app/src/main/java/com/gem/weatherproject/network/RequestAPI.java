package com.gem.weatherproject.network;

import com.gem.weatherproject.DTO.ListWeatherDTO;
import com.gem.weatherproject.DTO.WeatherDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by apple on 5/3/18.
 */

public interface RequestAPI {
  @GET("group")
  Call<ListWeatherDTO> getDataWeather(@Query("id") String id,
                                      @Query("appid") String appId);

  @GET("weather")
  Call<WeatherDTO> getDataByLocation(@Query("lat") double lat,
                                     @Query("lon") double lon,
                                     @Query("appid") String appId);
}
