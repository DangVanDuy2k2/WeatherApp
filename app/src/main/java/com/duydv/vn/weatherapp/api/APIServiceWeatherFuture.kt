package com.duydv.vn.weatherapp.api

import com.duydv.vn.weatherapp.model.weatherfuture.WeatherFuture
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceWeatherFuture {
    //https://api.openweathermap.org/data/2.5/forecast?q=london&appid=ca9f0036e70b0a5ce69234966b033a01&units=metric
    @GET("forecast")
    fun getDataWeatherFuture(@Query("q") q:String,
                            @Query("appid") appid:String,
                            @Query("units") units:String) : Call<WeatherFuture>
}