package com.brittlepins.brittlepins.network

import android.util.Log
import com.brittlepins.brittlepins.authentication.login.LogIn

class AuthServices {
    companion object {
        fun logIn(logIn: LogIn) {
            Log.d("AUTH_SERVICES", "Called logIn with ${logIn.email}")
        }
    }
}
