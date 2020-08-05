package com.brittlepins.brittlepins.authentication

data class User(
    val id: String,
    val email: String,
    val username: String,
    val token: String,
    val csrf: String
)
