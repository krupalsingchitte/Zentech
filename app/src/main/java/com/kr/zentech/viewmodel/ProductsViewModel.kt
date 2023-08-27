package com.kr.zentech.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kr.zentech.model.ProductListResponse
import com.kr.zentech.model.Products
import com.kr.zentech.retrofitApi.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<Products>>()
    val productsLiveData: LiveData<List<Products>> = _productsLiveData

    private val _searchResultsLiveData = MutableLiveData<List<Products>>()
    val searchResultsLiveData: LiveData<List<Products>> = _searchResultsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData









    fun loadProducts() {
        val call = RetrofitHelper.apiService.getProducts()

        call.enqueue(object : Callback<ProductListResponse> {
            override fun onResponse(
                call: Call<ProductListResponse>,
                response: Response<ProductListResponse>
            ) {
                if (response.isSuccessful) {
                    val productApiResponse = response.body()
                    val products = productApiResponse?.products ?: emptyList()
                    _productsLiveData.value = products


                } else {
                    _errorLiveData.value = "Error while load product"



                }
            }

            override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                _errorLiveData.value = "Network error: ${t.message}"

            }
        })
    }


    fun searchProducts(query: String) {
        val call = RetrofitHelper.apiService.searchProducts(query)

        call.enqueue(object : Callback<ProductListResponse> {
            override fun onResponse(
                call: Call<ProductListResponse>,
                response: Response<ProductListResponse>
            ) {
                if (response.isSuccessful) {
                    val productApiResponse = response.body()
                    val searchResults = productApiResponse?.products ?: emptyList()
                    _searchResultsLiveData.value = searchResults

                } else {
                    _errorLiveData.value = "Error while search product"

                }
            }

            override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                _errorLiveData.value = "Network error: ${t.message}"

            }
        })
    }

}
