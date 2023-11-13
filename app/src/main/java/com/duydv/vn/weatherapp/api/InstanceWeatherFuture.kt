package com.duydv.vn.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object InstanceWeatherFuture {
    //https://api.openweathermap.org/data/2.5/forecast?q=london&appid=ca9f0036e70b0a5ce69234966b033a01&units=metric
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServiceWeatherFuture:APIServiceWeatherFuture = retrofit.create(APIServiceWeatherFuture::class.java)
}