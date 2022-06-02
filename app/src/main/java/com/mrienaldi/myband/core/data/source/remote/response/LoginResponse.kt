package com.mrienaldi.myband.core.data.source.remote.response

import com.mrienaldi.myband.core.data.source.model.User

class LoginResponse(
    val code:Int?       = null,
    val message:String? = null,
    val data: User?     = null
)