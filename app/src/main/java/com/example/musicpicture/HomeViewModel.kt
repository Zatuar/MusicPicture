package com.example.musicpicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val text: MutableLiveData<String> = MutableLiveData()
    init {
        text.value = "This is home Fragment"
    }
}