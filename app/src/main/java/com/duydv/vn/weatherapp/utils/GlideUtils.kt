package com.duydv.vn.weatherapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.duydv.vn.weatherapp.R

object GlideUtils {
    fun loadImage(context:Context, icon:String, imageView:ImageView){
        //https://openweathermap.org/img/wn/10d@2x.png
        val urlImage = "https://openweathermap.org/img/wn/$icon.png"
        Glide.with(context)
            .load(urlImage)
            .error(R.drawable.cloudy_sunny)
            .into(imageView)
    }
}