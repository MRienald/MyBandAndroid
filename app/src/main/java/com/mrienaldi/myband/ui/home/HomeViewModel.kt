package com.mrienaldi.myband.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrienaldi.myband.core.data.source.local.Dummydata
import com.mrienaldi.myband.core.data.source.model.Catagory
import com.mrienaldi.myband.core.data.source.model.Product
import java.util.*

class HomeViewModel : ViewModel() {

    val listProduct : LiveData<List<Product>> = MutableLiveData<List<Product>>().apply {
        value = Dummydata.listProduct
    }

}