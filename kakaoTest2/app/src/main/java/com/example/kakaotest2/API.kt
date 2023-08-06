package com.example.kakaotest2

import com.example.kakaotest2.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.*

public interface API {
    // login
    @POST("android")
    fun getLoginResponse(@Body user: User): Call<String>

}