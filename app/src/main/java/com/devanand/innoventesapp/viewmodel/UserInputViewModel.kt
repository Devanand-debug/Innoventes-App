package com.devanand.innoventesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devanand.innoventesapp.model.UserInput
import com.devanand.innoventesapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserInputViewModel(private val repository: UserRepository) : ViewModel() {

    private val _validationResult = MutableLiveData<String>()
    val validationResult: LiveData<String> get() = _validationResult

    private val _isNextButtonVisible = MutableLiveData<Boolean>()
    val isNextButtonVisible: LiveData<Boolean> get() = _isNextButtonVisible

    init {
        _isNextButtonVisible.value = false
    }

    fun validateUserInput(pan: String, day: String, month: String, year: String) {
   // fun validateUserInput(userInput: UserInput) {
        viewModelScope.launch {
            val isValid = repository.validateInput(pan, day, month, year)
           // val isValid = repository.validateInput(userInput)
            _isNextButtonVisible.value = isValid
            _validationResult.value = if (isValid) "Valid input" else "Invalid input"
        }
    }

}