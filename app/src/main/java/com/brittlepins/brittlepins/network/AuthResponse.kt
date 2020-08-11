package com.brittlepins.brittlepins.network

data class AuthResponse(
    val token: String,
    val csrf: String
)
