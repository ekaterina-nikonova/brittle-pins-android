package com.example.brittlepins.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brittlepins.BuildConfig;
import com.example.brittlepins.R;
import com.example.brittlepins.api.model.Login;
import com.example.brittlepins.api.model.User;
import com.example.brittlepins.api.service.UserClient;
import com.example.brittlepins.helpers.AuthServices;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEditText = findViewById(R.id.usernameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
    }

    public void logIn(View view) {
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        Login login = new Login(username, password);
        Call<User> call = AuthServices.userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    showToast("Successfully logged in");

                    String token = response.body().getCSRF();
                    MainActivity.sharedPreferences.edit().putString("csrf", token).apply();
                    showToast(token);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    showToast("Something went wrong, and we failed to log in.");
                    Log.e("Log in response", response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showToast("Could not log in.");
                Log.e("Log in failure", t.getMessage());
                t.printStackTrace();
            }
        });
    }


    private

    void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}