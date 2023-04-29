package com.example.sunnyweather.ui.place

import Place
import androidx.lifecycle.*
import com.example.sunnyweather.logic.Repository

class PlaceViewModel : ViewModel() {
    //对外部设置为不可见,防止改变livedata里的数据
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()

    //如果ViewModel中的某个LiveData对象是调用另外的方法获取的,那么我们就可以借助switchMap()方法,将这个LiveData对象转换成另外一个可观察的LiveData对象
    //使用switchMap是为了将大量冗余的不需要的数据去掉,剩下有用的数据
    val placeLiveData = searchLiveData.switchMap() { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}