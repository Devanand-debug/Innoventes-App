package com.devanand.innoventesapp.repository

import com.devanand.innoventesapp.model.UserInput
import com.devanand.innoventesapp.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    
    suspend fun validateInput(pan : String, day : String, month : String, year : String): Boolean{
   // suspend fun validateInput(userInput: UserInput): Boolean{

        return withContext(Dispatchers.Main){
            val isValidPan = Utils.validatePan(pan)
            val isValidDate = Utils.validateDate(day, month, year)
            isValidPan && isValidDate
        }
    }


}