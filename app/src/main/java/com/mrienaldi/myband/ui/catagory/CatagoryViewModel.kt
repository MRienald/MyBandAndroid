package com.mrienaldi.myband.ui.catagory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatagoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Catagory Fragment"
    }
    val text: LiveData<String> = _text
}