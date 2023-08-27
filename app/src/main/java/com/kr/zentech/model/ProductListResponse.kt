package com.kr.zentech.model

data class ProductListResponse(
    val products: List<Products>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
