package com.brittlepins.brittlepins.network

import android.content.Context
import android.util.Log
import com.brittlepins.brittlepins.BuildConfig
import com.brittlepins.brittlepins.authentication.login.LogIn
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG = "AuthServices"

class AuthServices {
    companion object {
        @Volatile
        private var CLIENT_INSTANCE: UserClient? = null
        lateinit var jar: PersistentCookieJar

        fun getClient(context: Context): UserClient {
            synchronized(this) {
                var instance = CLIENT_INSTANCE

                if (instance == null) {
                    instance = buildClient(context)
                }
                return instance
            }
        }

        fun logIn(context: Context, logInData: LogIn) {
            val call = getClient(context).signIn(logInData)
            call.enqueue(object: Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d(TAG, "Call failed: $call")
                    reset()
                    t.printStackTrace()
                    // TODO("Show snackbar (write to LiveData?)")
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Response successful: ${response.message()}")
                        val csrfToken = response.body()?.csrf
                        context.getSharedPreferences("cookie_prefs", Context.MODE_PRIVATE)
                            .edit()
                            .putString("csrf", csrfToken.toString())
                            .apply()

                        // TODO("Navigate to Main (write to LiveData?)")
                    } else {
                        Log.d(TAG, "Response not successful: ${response.message()}")
                        // response.errorBody()?.string() -> {"error":"No invitation found"}
                        Log.d(TAG, "Response not successful: ${response.errorBody()?.string()}")
                        reset()
                        // TODO("Show snackbar (write to LiveData?)")
                    }
                }
            })
        }

        private fun buildClient(context: Context) : UserClient {
            val prefs = context
                .getSharedPreferences("cookie_prefs", Context.MODE_PRIVATE)

            jar = PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(prefs)
            )

            val client = OkHttpClient.Builder().cookieJar(jar).build()

            val retrofit = Retrofit.Builder().apply {
                baseUrl("${BuildConfig.BASE_URL}/api/v1/")
                client(client)
                addConverterFactory(GsonConverterFactory.create())
            }.build()

            // For host verification
            val baseUrl = retrofit.baseUrl()
            val scheme = baseUrl.scheme
            val host = baseUrl.host
            context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString("host", "$scheme://$host")
                .apply()

            return retrofit.create(UserClient::class.java)
        }

        private fun reset() = jar.clear()
    }
}
