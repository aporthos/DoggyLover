package com.portes.doggylover.core.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogNetwork(
    @Json(name = "dogName")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "age")
    val age: Int,
    @Json(name = "image")
    val image: String,
)
