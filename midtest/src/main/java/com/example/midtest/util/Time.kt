package com.example.midtest.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Time() {
    companion object {
        //获取当地时间
        @RequiresApi(Build.VERSION_CODES.O)
        val now: LocalDate = LocalDate.now()
           //当地时间格式
        @RequiresApi(Build.VERSION_CODES.O)
        val dataString: String = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
         //请求前些天的
        @RequiresApi(Build.VERSION_CODES.O)
        fun getYesterday(day: Long): String =
            now.minusDays(day).format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    }

}