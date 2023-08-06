package com.example.kakaotest2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api: API
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.45.219:8080/") // 요청 보내는 API 서버 url. /로 끝나야 함함
            .addConverterFactory(GsonConverterFactory.create()) // Gson을 역직렬화
            .build()
        api = retrofit.create(API::class.java)
    }
}