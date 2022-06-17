package com.mrienaldi.myband.core.data.repository

import android.util.Log
import com.inyongtisto.myhelper.extension.getErrorBody
import com.mrienaldi.myband.core.data.source.local.LocalDataSource
import com.mrienaldi.myband.core.data.source.remote.RemoteDataSource
import com.mrienaldi.myband.core.data.source.remote.network.Resource
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.core.data.source.remote.request.RegisterRequest
import com.mrienaldi.myband.core.data.source.remote.request.UpdateProfileRequest
import com.mrienaldi.myband.util.Prefs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local : LocalDataSource, val remote : RemoteDataSource) {

    fun login(data:LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    Log.d("TAG", "success:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    Log.d("TAG", "Error:" + "Keterangan error")
                }
            }
        } catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun register(data:RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful){
//                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
//                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    Log.d("TAG", "success:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    Log.d("TAG", "Error:" + "Keterangan error")
                }
            }
        } catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun updateUser(data:UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful){
//                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    Log.d("TAG", "success:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    Log.d("TAG", "Error:" + "Keterangan error")
                }
            }
        } catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadUser(id:Int? = null, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful){
//                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    Log.d("TAG", "success:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    Log.d("TAG", "Error:" + "Keterangan error")
                }
            }
        } catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

//    class ErrorCustom(
//        val ok: Boolean,
//        val error_code: Int,
//        val description: String? = null
//    )

}