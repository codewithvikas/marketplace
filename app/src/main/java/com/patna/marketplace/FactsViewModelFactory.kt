package com.patna.marketplace

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patna.marketplace.model.FactCategory
import com.patna.marketplace.model.FactDao
import java.lang.IllegalArgumentException

class FactsViewModelFactory(private val factDao: FactDao,val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactsViewModel::class.java)){
            return FactsViewModel(factDao,application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }


}