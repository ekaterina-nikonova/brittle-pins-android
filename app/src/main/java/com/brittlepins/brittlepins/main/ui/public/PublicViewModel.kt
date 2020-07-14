package com.brittlepins.brittlepins.main.ui.public

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PublicViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is public Fragment"
    }
    val text: LiveData<String> = _text
}
