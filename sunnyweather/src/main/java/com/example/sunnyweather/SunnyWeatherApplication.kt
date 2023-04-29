package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

//Android提供了一个Application类,每当应用程序启动的时候,系统会自动将这个类初始化
//而我们可以定制一个自己的Application类,以便于管理程序内一些全局的状态信息
//比如全局Context
class SunnyWeatherApplication : Application() {
    //静态全局变量,可以被外部访问到
    companion object {
        const val TOKEN = "Ad98GhF0nF5GP1Jw"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}