package com.github.andreldsr.librarymanager.modules.user.request

data class RegisterRequest(
    val login: String,
    val name: String,
    val password: String,
    val role: String,
    val description: String? = null,
)
