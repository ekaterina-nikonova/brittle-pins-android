package com.brittlepins.brittlepins.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {
    val email = MutableLiveData<String>()
}