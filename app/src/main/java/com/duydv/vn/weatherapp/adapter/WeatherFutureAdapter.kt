package com.duydv.vn.weatherapp.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.duydv.vn.weatherapp.R
import com.duydv.vn.weatherapp.databinding.ItemWeatherFutureBinding
import com.duydv.vn.weatherapp.model.weatherfuture.WeatherFuture
import com.duydv.vn.weatherapp.model.weatherfuture.WeatherFutureData
import com.duydv.vn.weatherapp.utils.GlideUtils
import com.duydv.vn.weatherapp.utils.StringUtils

class WeatherFutureAdapter(private val context:Context,private val timezone:Int, private val mListWeatherFuture: MutableList<WeatherFutureData>) : RecyclerView.Adapter<WeatherFutureAdapter.WeatherFutureViewHolder>() {
    inner class WeatherFutureViewHolder(val itemWeatherFutureBinding: ItemWeatherFutureBinding) : ViewHolder(itemWeatherFutureBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherFutureViewHolder {
        val itemWeatherFutureBinding = ItemWeatherFutureBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherFutureViewHolder(itemWeatherFutureBinding)
    }

    override fun getItemCount(): Int {
        return mListWeatherFuture.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherFutureViewHolder, position: Int) {
        val weatherFutureData = mListWeatherFuture[position]

        val strTemp = weatherFutureData.main.temp.toInt().toString() + context.getString(R.string.char_special)
        holder.itemWeatherFutureBinding.txtTemp.text = strTemp

        GlideUtils.loadImage(context,weatherFutureData.weather[0].icon,holder.itemWeatherFutureBinding.imgWeather)

        val strTimeFuture = StringUtils.convertTimestampToHour(weatherFutureData.dt,timezone)
        holder.itemWeatherFutureBinding.txtHour.text = strTimeFuture
    }
}