package com.mrienaldi.myband.core.data.source.remote.network

import android.app.DownloadManager
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.core.data.source.remote.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login : LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        //@Body user : User
    ): Response<RequestBody>
}