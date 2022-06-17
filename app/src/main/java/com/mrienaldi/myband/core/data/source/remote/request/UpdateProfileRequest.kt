package com.mrienaldi.myband.core.data.source.remote.request

import java.net.PasswordAuthentication
import java.util.*

data class UpdateProfileRequest(
    val id          : Int,
    val username    : String? = null,
    val name        : String? = null,
    val email       : String? = null,
    val phone       : String? = null,
    val birthday    : String? = null,
    val address     : String? = null,
    )