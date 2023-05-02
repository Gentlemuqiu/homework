package com.example.midtest.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Time() {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val now: LocalDate = LocalDate.now()

        @RequiresApi(Build.VERSION_CODES.O)
        val dataString: String = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        @RequiresApi(Build.VERSION_CODES.O)
        fun getYesterday(day: Long): String =
            now.minusDays(day).format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    }

}