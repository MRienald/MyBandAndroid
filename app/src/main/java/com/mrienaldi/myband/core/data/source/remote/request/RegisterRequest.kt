package com.mrienaldi.myband.core.data.source.remote.request

import java.net.PasswordAuthentication
import java.util.*

data class RegisterRequest(
    val username: String,
    val name: String,
    val email: String,
    val phone: String,
    val birthday: String,
    val address: String,
    val password: String
    )