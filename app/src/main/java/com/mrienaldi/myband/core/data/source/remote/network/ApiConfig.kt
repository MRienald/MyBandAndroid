package com.mrienaldi.myband.core.data.source.remote.network

import android.graphics.Bitmap
import com.google.gson.GsonBuilder
import com.mrienaldi.myband.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private const val BASE_URL = Constants.BASE_URL + "api/"

    private val client : Retrofit
        get() {
            val gson = GsonBuilder().setLenient().create()

            val timout = 40L

            val interceptor             = HttpLoggingInterceptor()
            interceptor.level           = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient    = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .build()
        }

    val provideApiService : ApiService
        get() = client.create(ApiService::class.java)

}