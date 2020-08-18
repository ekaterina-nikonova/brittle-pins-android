package com.brittlepins.brittlepins.network

import com.brittlepins.brittlepins.authentication.login.LogIn
import com.brittlepins.brittlepins.db.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserClient {
    @POST("signin")
    fun signIn(@Body signIn: LogIn) : Call<AuthResponse>

    @GET("me")
    fun getMe() : Call<User>
}
