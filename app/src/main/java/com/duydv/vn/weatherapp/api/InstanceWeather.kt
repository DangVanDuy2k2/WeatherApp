package com.duydv.vn.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceWeather {
    //https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=ca9f0036e70b0a5ce69234966b033a01&units=metric

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServiceWeather: APIServiceWeather = retrofit.create(APIServiceWeather::class.java)
}