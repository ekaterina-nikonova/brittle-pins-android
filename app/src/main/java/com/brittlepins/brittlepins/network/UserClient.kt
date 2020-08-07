package com.brittlepins.brittlepins.network

import com.brittlepins.brittlepins.db.User
import com.brittlepins.brittlepins.authentication.login.LogIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserClient {
    @POST("signin")
    fun signIn(@Body signIn: LogIn) : Call<User>
}
