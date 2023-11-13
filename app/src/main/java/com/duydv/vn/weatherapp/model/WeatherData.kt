package com.duydv.vn.weatherapp.model

data class WeatherData(
    val weather: List<Weather>,
    val main:Main,
    val wind: Wind,
    val timezone:Int,
    val dt:Long,
    val name:String
)