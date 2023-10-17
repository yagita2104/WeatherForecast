package com.example.weatherforecast.api;

import com.example.weatherforecast.model.WeatherData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //https://api.weatherapi.com/v1/forecast.json?key=9946e28297874f61a90112012231203&q=HaNoi&days=2&aqi=yes&alerts=no
    Gson gson = new GsonBuilder().setDateFormat("dd-mm-yyyy HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("v1/forecast.json")
    Call<WeatherData> weatherData(@Query("key") String key,
                                  @Query("q") String q,
                                  @Query("days") int days,
                                  @Query("aqi") String aqi,
                                  @Query("alerts") String alerts);

}
