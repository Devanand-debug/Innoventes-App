package com.devanand.innoventesapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devanand.innoventesapp.repository.UserRepository
import com.devanand.innoventesapp.viewmodel.UserInputViewModel

class UserInputViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return super.create(modelClass)
        if (modelClass.isAssignableFrom(UserInputViewModel ::class.java)){
            return UserInputViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}