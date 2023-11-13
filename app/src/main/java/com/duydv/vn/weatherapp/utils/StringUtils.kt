package com.duydv.vn.weatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object StringUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTimestampToFormattedStringWithOffset(timestamp: Long, offsetSeconds: Int): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds)
        val localDateTime = LocalDateTime.ofInstant(instant, zoneOffset)

        // Định dạng thứ, tháng ngày, năm
        val dateFormat = DateTimeFormatter.ofPattern("E MMM dd, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(localDateTime)

        // Kết hợp ngày và giờ thành định dạng cuối cùng
        return formattedDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTimestampToHour(timestamp: Long, offsetSeconds: Int): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds)
        val localDateTime = LocalDateTime.ofInstant(instant, zoneOffset)

        // Định dạng thứ, tháng ngày, năm
        val dateFormat = DateTimeFormatter.ofPattern("h a", Locale.getDefault())
        val formattedDate = dateFormat.format(localDateTime)

        // Kết hợp ngày và giờ thành định dạng cuối cùng
        return formattedDate
    }
}