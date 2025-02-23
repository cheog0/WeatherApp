package com.yujun.weatherapp.network

import com.yujun.weatherapp.BuildConfig
import com.yujun.weatherapp.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.WEATHER_API_KEY

interface WeatherService {
    @GET("getVilageFcst?serviceKey=$API_KEY")
    suspend fun getWeather(
        @Query("base_date") baseDate: Int,
        @Query("base_time") baseTime: String = "0500",
        @Query("nx") nx: String = "60",
        @Query("ny") ny: String = "121",
        @Query("numOfRows") numOfRows: Int = 12,
        @Query("pageNo") pageNo: Int = 1,
        @Query("dataType") dataType: String = "JSON"
    ): WeatherModel
}
