package com.kr.zentech.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kr.zentech.model.LoginRequest
import com.kr.zentech.model.LoginResponse
import com.kr.zentech.retrofitApi.RetrofitHelper.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginResponseLiveData: LiveData<LoginResponse> = _loginResponseLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResponseLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Login failed"
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _errorLiveData.value = "Network error: ${t.message}"
            }
        })
    }
}
