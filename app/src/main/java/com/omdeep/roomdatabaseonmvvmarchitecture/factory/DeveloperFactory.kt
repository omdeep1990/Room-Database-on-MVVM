package com.omdeep.roomdatabaseonmvvmarchitecture.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.roomdatabaseonmvvmarchitecture.repository.DeveloperRepository
import com.omdeep.roomdatabaseonmvvmarchitecture.viewModel.MyViewModel
import java.lang.IllegalArgumentException

class DeveloperFactory(private val developerRepository: DeveloperRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(developerRepository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found!!")
    }
}