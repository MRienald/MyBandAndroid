package com.mrienaldi.myband.core.data.source.remote

import com.mrienaldi.myband.core.data.source.remote.network.ApiConfig
import com.mrienaldi.myband.core.data.source.remote.network.ApiService
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data: LoginRequest) = api.login(data)

}