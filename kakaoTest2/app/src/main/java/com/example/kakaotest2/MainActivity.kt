package com.example.kakaotest2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.kakaotest2.KakaoOauthViewModel.Companion.TAG
import com.kakao.sdk.user.UserApiClient
import com.google.gson.Gson
import com.example.kakaotest2.RetrofitBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var kakaoOauthViewModel: KakaoOauthViewModel
    var name: String = ""
    var email: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModelProvider를 통해 ViewModel 인스턴스 생성
        kakaoOauthViewModel = ViewModelProvider(this, KakaoOauthViewModelFactory(application)).get(KakaoOauthViewModel::class.java)

        val btnKakaoLogin = findViewById<Button>(R.id.btn_kakao_login)
        val btnKakaoLogout = findViewById<Button>(R.id.btn_kakao_logout)
        val tvLoginStatus = findViewById<TextView>(R.id.tv_login_status)

        btnKakaoLogin.setOnClickListener {
            kakaoOauthViewModel.kakaoLogin()
        }

        btnKakaoLogout.setOnClickListener {
            kakaoOauthViewModel.kakaoLogout()
        }

        kakaoOauthViewModel.isLoggedIn.asLiveData().observe(this) { isLoggedIn ->
            val loginStatusInfoTitle = if (isLoggedIn) "로그인 상태" else "로그아웃 상태"
            tvLoginStatus.text = loginStatusInfoTitle
        }

        val test = findViewById<Button>(R.id.buttonInfo)
        test.setOnClickListener {
            getInfo()
        }


    }

    fun getInfo(){
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" )

                name = user.kakaoAccount?.profile?.nickname.toString()
                email = user.kakaoAccount?.email.toString()
                val userInfo = User()
                userInfo.name = user.kakaoAccount?.profile?.nickname.toString()
                userInfo.email = user.kakaoAccount?.email.toString()
                Log.d("BUTTON CLICKED", "id: " + userInfo.name + ", pw: " + userInfo.email)

                Login(userInfo)
            }
        }
    }

    fun Login(user: User){

        val call = RetrofitBuilder.api.getLoginResponse(user)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())

                }else{
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

}