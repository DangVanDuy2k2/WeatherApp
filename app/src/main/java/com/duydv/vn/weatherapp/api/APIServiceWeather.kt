package com.duydv.vn.weatherapp.api

import com.duydv.vn.weatherapp.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceWeather {
    //https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=ca9f0036e70b0a5ce69234966b033a01&units=metric
    @GET("weather")
    fun getData(@Query("q") q:String,
                @Query("appid") appid:String,
                @Query("units") units:String) : retrofit2.Call<WeatherData>
}