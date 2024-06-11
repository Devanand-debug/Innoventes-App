package com.devanand.innoventesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devanand.innoventesapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserInputViewModel(private val repository: UserRepository) : ViewModel() {

        private val _validationResult = MutableLiveData<String>()
        val validationResult: LiveData<String> get() = _validationResult

        fun validateUserInput(pan: String, day: String, month: String, year: String) {
            viewModelScope.launch {
                val isValid = repository.validateInput(pan, day, month, year)
                _validationResult.value = if (isValid) "Valid input" else "Invalid input"
            }
        }

    }