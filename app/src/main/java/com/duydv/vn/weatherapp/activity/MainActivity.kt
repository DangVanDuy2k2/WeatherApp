package com.duydv.vn.weatherapp.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.duydv.vn.weatherapp.R
import com.duydv.vn.weatherapp.adapter.WeatherFutureAdapter
import com.duydv.vn.weatherapp.api.InstanceWeather
import com.duydv.vn.weatherapp.api.InstanceWeatherFuture
import com.duydv.vn.weatherapp.constant.Constant
import com.duydv.vn.weatherapp.databinding.ActivityMainBinding
import com.duydv.vn.weatherapp.model.WeatherData
import com.duydv.vn.weatherapp.model.weatherfuture.WeatherFuture
import com.duydv.vn.weatherapp.model.weatherfuture.WeatherFutureData
import com.duydv.vn.weatherapp.utils.GlideUtils
import com.duydv.vn.weatherapp.utils.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var mListWeatherFuture:MutableList<WeatherFutureData> = ArrayList()
    private lateinit var mWeatherFutureAdapter: WeatherFutureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)

        startUpdatingWeather()
    }

    private fun callAPI(){
        val callWeatherAPI = InstanceWeather.apiServiceWeather.getData("luanda",Constant.key_API,Constant.units)

        callWeatherAPI.enqueue(object :Callback<WeatherData>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherData = response.body()
                if(weatherData != null){
                    mActivityMainBinding.txtCityName.text = weatherData.name

                    mActivityMainBinding.txtWeather.text = weatherData.weather[0].main

                    val strTemp = weatherData.main.temp.toInt().toString() + getString(R.string.char_special)
                    mActivityMainBinding.txtTemp.text = strTemp

                    val strPressure = weatherData.main.pressure.toString() + " " + getString(R.string.hPa)
                    mActivityMainBinding.txtPressure.text = strPressure

                    val strWindSpeed = weatherData.wind.speed.toString() + " " + getString(R.string.kmh)
                    mActivityMainBinding.txtWindSpeed.text = strWindSpeed

                    val strPercentHumidity = weatherData.main.humidity.toString() + " " + getString(R.string.percent)
                    mActivityMainBinding.txtPercentHumidity.text = strPercentHumidity

                    val strDate = StringUtils.convertTimestampToFormattedStringWithOffset(weatherData.dt,weatherData.timezone)
                    mActivityMainBinding.txtDatetime.text = strDate

                    GlideUtils.loadImage(this@MainActivity,weatherData.weather[0].icon,mActivityMainBinding.imgWeather)
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Toast.makeText(this@MainActivity,"onFailure",Toast.LENGTH_SHORT).show()
            }
        })

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mActivityMainBinding.rcvWeather.layoutManager = linearLayoutManager


        val callWeatherFutureAPI = InstanceWeatherFuture.apiServiceWeatherFuture.getDataWeatherFuture("luanda",Constant.key_API,Constant.units)
        callWeatherFutureAPI.enqueue(object :Callback<WeatherFuture>{
            override fun onResponse(call: Call<WeatherFuture>, response: Response<WeatherFuture>) {
                Toast.makeText(this@MainActivity,"onResponse",Toast.LENGTH_SHORT).show()
                val weatherFuture = response.body()
                if(weatherFuture != null){
                    mListWeatherFuture = weatherFuture.list
                    mWeatherFutureAdapter = WeatherFutureAdapter(this@MainActivity,weatherFuture.city.timezone,mListWeatherFuture)
                    mActivityMainBinding.rcvWeather.adapter = mWeatherFutureAdapter
                }
            }

            override fun onFailure(call: Call<WeatherFuture>, t: Throwable) {
                Toast.makeText(this@MainActivity,"onFailure",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startUpdatingWeather() {
        // Tạo và chạy Runnable để cập nhật thời tiết sau mỗi 1 tiếng
        handler.post(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                callAPI()
                handler.postDelayed(this, 1000*60*60)
            }
        })
    }
}