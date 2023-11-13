package com.duydv.vn.weatherapp.model.weatherfuture

import com.duydv.vn.weatherapp.model.Main
import com.duydv.vn.weatherapp.model.Weather

data class WeatherFutureData(val dt:Long, val main:Main, val weather:List<Weather>) {
}