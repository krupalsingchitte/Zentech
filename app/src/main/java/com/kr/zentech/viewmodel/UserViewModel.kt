package com.kr.zentech.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kr.zentech.model.UserInfo
import com.kr.zentech.retrofitApi.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {


    private val _userInfoLiveData = MutableLiveData<UserInfo?>()
    val userInfoLiveData: MutableLiveData<UserInfo?> = _userInfoLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun fetchUserInfo(userId: Int) {
        val call = RetrofitHelper.apiService.getUserById(userId)

        call.enqueue(object : Callback<UserInfo> {
            override fun onResponse(
                call: Call<UserInfo>,
                response: Response<UserInfo>
            ) {
                if (response.isSuccessful) {
                    val userInfo = response.body()
                    _userInfoLiveData.value = userInfo
                } else {
                    _errorLiveData.value = "Error fetching user information"
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                _errorLiveData.value = "Network error: ${t.message}"
            }
        })
    }


}
