package com.yujun.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yujun.weatherapp.data.WeatherData
import com.yujun.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "WeatherViewModel"

class WeatherViewModel(private val repository: WeatherRepository = WeatherRepository()) : ViewModel() {
    private val _regionText = MutableLiveData("서울특별시")
    val regionText: LiveData<String> = _regionText

    fun setRegion(city: String) {       // WeatherHomeFragment popupwindow viewModel.setRegion(selectedCity)
        _regionText.value = city
    }

    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData get() = _weatherData

    private val _weatherList = MutableLiveData<List<WeatherData>>()
    val weatherList get() = _weatherList

    fun getWeather(date: String, city: String = regionText.value ?: "서울특별시") {  // Repository에게 데이터를 가지고 오라고 요청
        viewModelScope.launch {
            runCatching {
                repository.getWeather(date, city)       // regionText.value가 바뀌면 이 함수를 실행해서 새로운 정보 가지고오기
            }.onSuccess { weatherResponse ->
                _weatherData.value = weatherResponse.toWeatherData()        //weatherModel == weatherInstance,weatherModel에 있는 toWeatherData
            }.onFailure { e ->
                handleException(e)
            }
        }
    }

    fun getWeatherList(date: String, count: Int = 20) {
        viewModelScope.launch {
            runCatching {
                val city = regionText.value ?: "서울특별시"
                repository.getWeather(date, city, pageNo = count)
            }.onSuccess { weatherResponse ->
                _weatherList.value = weatherResponse.toWeatherList(count)
            }.onFailure { e ->
                handleException(e)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is HttpException -> {
                val errorJsonString = e.response()?.errorBody()?.string()
                Log.e(TAG, "HTTP error: $errorJsonString")
            }

            is IOException -> Log.e(TAG, "Network error: $e")
            else -> Log.e(TAG, "Unexpected error: $e")
        }
    }
}