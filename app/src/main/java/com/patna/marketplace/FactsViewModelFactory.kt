package com.patna.marketplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patna.marketplace.model.FactCategory
import java.lang.IllegalArgumentException

class FactsViewModelFactory(private val factCategory: FactCategory):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactsViewModel::class.java)){
            return FactsViewModel(factCategory) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }


}