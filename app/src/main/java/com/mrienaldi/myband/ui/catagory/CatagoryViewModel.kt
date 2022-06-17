package com.mrienaldi.myband.ui.catagory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrienaldi.myband.core.data.source.local.Dummydata
import com.mrienaldi.myband.core.data.source.model.Catagory

class CatagoryViewModel : ViewModel() {

    val listCatagory : LiveData<List<Catagory>> = MutableLiveData<List<Catagory>>().apply {
        value = Dummydata.listCatagory
    }
}