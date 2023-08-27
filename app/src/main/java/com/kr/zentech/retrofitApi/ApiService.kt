package com.kr.zentech.retrofitApi

import com.kr.zentech.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("products")
    fun getProducts(): Call<ProductListResponse>

    @GET("products/search")
    fun searchProducts(@Query("q") query: String): Call<ProductListResponse>

    @GET("users/{id}")
    fun getUserById(@Path("id") userId: Int): Call<UserInfo>


}