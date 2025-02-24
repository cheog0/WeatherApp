package com.yujun.weatherapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { //요청이 잘 보내졌는지 확인하는 로그를 출력하기 위해 네트워크 요청을 가로채는 기능을 제공
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: WeatherService by lazy { retrofit.create(WeatherService::class.java) }
}