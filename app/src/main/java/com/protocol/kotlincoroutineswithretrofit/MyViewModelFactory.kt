package com.protocol.kotlincoroutineswithretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.protocol.kotlincoroutineswithretrofit.repository.MainRepository
import com.protocol.kotlincoroutineswithretrofit.viewmodel.MainViewModel

class MyViewModelFactory constructor(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}