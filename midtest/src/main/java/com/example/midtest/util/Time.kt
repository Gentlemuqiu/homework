package com.example.midtest.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Time() {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val now = LocalDate.now()

        @RequiresApi(Build.VERSION_CODES.O)
        val dataString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        @RequiresApi(Build.VERSION_CODES.O)
        fun getYesterday(day: Long) =
            now.minusDays(day).format(DateTimeFormatter.ofPattern("yyyyMMdd"))


    }

}