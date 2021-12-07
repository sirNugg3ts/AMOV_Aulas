package com.a21280348.webcoms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyModel : ViewModel() {
    val apiKey = "b23c3f83574c4abbaeb144225210712"

    val webContent : MutableLiveData<String?> = MutableLiveData()

    fun updateWeatherInfo(){
        NetUtils.getDataAsync("https://api.weatherapi.com/v1/forecast.json?key=$apiKey&q=Coimbra&days=2&aqi=no&alerts=no",webContent)
    }
}