package com.griyanet.submission2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.griyanet.submission2.Model.Repository

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }


}