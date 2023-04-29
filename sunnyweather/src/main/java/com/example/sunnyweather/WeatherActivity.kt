package com.example.sunnyweather

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sunnyweather.databinding.ActivityWeatherBinding
import com.example.sunnyweather.logic.model.Weather
import com.example.sunnyweather.logic.model.getSky
import com.example.sunnyweather.ui.weather.WeatherViewModel

class WeatherActivity : AppCompatActivity() {
        private val mBinding: ActivityWeatherBinding by lazy {
            ActivityWeatherBinding.inflate(layoutInflater)
        }


    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //使背景图和状态栏融合到一起
        val decorView = window.decorView
        //表示Activity的布局会显示在状态栏上面,
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //将状态栏显示为透明色
        window.statusBarColor = Color.TRANSPARENT
        setContentView(mBinding.root)

        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            mBinding.swipeRefresh.isRefreshing = false
        })
        mBinding.nowLayout.navBtn.setOnClickListener {
            mBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        mBinding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(
                    drawerView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })

        mBinding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        refreshWeatherOwn()
        mBinding.swipeRefresh.setOnRefreshListener {
            refreshWeatherOwn()
        }
    }

    fun refreshWeatherOwn() {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        mBinding.swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
        mBinding.nowLayout.placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        //填充now.xml布局中的数据
        val currentTempText = "${realtime.temperature.toInt()}℃"
        mBinding.nowLayout.currentTemp.text = currentTempText
        mBinding.nowLayout.currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数${realtime.airQuality.aqi.chn.toInt()}"
        mBinding.nowLayout.currentAQI.text = currentPM25Text
        mBinding.nowLayout.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        //填充forecast.xml布局中的数据
        mBinding.forecastLayout.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(
                R.layout.forcast_item,
                mBinding.forecastLayout.forecastLayout, false
            )
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            // val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            //dateInfo.text = simpleDateFormat.format(skycon.date)
            dateInfo.text = skycon.date
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()}"
            temperatureInfo.text = tempText
            mBinding.forecastLayout.forecastLayout.addView(view)
        }
        //填充life_index.xml的数据
        val lifeIndex = daily.lifeIndex
        mBinding.lifeIndexLayout.coldRiskText.text = lifeIndex.coldRisk[0].desc
        mBinding.lifeIndexLayout.dressingText.text = lifeIndex.dressing[0].desc
        mBinding.lifeIndexLayout.ultravioletText.text = lifeIndex.ultraviolet[0].desc
        mBinding.lifeIndexLayout.carWashingText.text = lifeIndex.carWashing[0].desc
        mBinding.weatherLayout.visibility = View.VISIBLE
    }
}