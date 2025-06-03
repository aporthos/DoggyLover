package com.portes.doggylover.core.data

import com.portes.doggylover.core.models.network.DogNetwork
import retrofit2.http.GET

interface ApiServices {
    @GET("api/1151549092634943488")
    suspend fun getDogs(): List<DogNetwork>
}
