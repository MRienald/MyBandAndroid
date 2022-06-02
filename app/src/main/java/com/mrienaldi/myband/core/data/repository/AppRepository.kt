package com.mrienaldi.myband.core.data.repository

import android.util.Log
import com.mrienaldi.myband.core.data.source.local.LocalDataSource
import com.mrienaldi.myband.core.data.source.remote.RemoteDataSource
import com.mrienaldi.myband.core.data.source.remote.network.Resource
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local : LocalDataSource, val remote : RemoteDataSource) {

    fun login(data:LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body?.data))
                    Log.d("TAG", "success:" + body.toString())
                } else {
                    emit(Resource.error("Error default", null))
                    Log.d("TAG", "Error:" + "Keterangan error")
                }
            }
        } catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

}