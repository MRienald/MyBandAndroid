package com.mrienaldi.myband.core.data.source.model

data class Product(
    val id: String? = null,
    val name: String?,
    val price: Int?,
    val genre: String?,
    val rating: Double?,
    val type: String,
    val desc: String?,
    val imageproduct: Int,
)
