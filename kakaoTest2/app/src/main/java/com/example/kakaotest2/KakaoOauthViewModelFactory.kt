package com.example.kakaotest2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application

class KakaoOauthViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KakaoOauthViewModel::class.java)) {
            return KakaoOauthViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}