package com.mrienaldi.myband.core.data.source.remote.network

import android.app.DownloadManager
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.core.data.source.remote.request.RegisterRequest
import com.mrienaldi.myband.core.data.source.remote.request.UpdateProfileRequest
import com.mrienaldi.myband.core.data.source.remote.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login : LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data : RegisterRequest
    ): Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int,
        @Body data : UpdateProfileRequest
    ): Response<LoginResponse>

    @Multipart
    @POST("upload-profile-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int? = null,
        @Part data : MultipartBody.Part? = null
    ): Response<LoginResponse>
}