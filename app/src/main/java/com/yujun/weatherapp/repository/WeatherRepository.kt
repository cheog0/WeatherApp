package com.yujun.weatherapp.repository

import com.yujun.weatherapp.data.cityList
import com.yujun.weatherapp.model.WeatherModel
import com.yujun.weatherapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {
    suspend fun getWeather(date: String, cityName: String, pageNo: Int = 1): WeatherModel { // 특정 날짜와 도시 이름을 기반으로 API 요청을 보내고, 그 결과를 weatherModel로 반환
        return withContext(Dispatchers.IO) {
            val city = cityList.find { it.name == cityName } ?: cityList.first()
            RetrofitInstance.service.getWeather(        // 실제 구현체를 이용하여 API 요청
                baseDate = date.toInt(),
                nx = city.nx.toString(),
                ny = city.ny.toString(),
                numOfRows = pageNo * 12
            )
        }
    }
}