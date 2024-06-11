package com.devanand.innoventesapp.utils

import java.util.Calendar

object Utils {

    fun validatePan(pan: String): Boolean {
        val panRegex = "[A-Z]{5}[0-9]{4}[A-Z]{1}".toRegex()
        return pan.matches(panRegex)
    }

     fun validateDate(day: String, month: String, year: String): Boolean {
        if (day.isEmpty() || month.isEmpty() || year.isEmpty()) return false
        val dayInt = day.toIntOrNull() ?: return false
        val monthInt = month.toIntOrNull() ?: return false
        val yearInt = year.toIntOrNull() ?: return false

        if (dayInt !in 1..31 || monthInt !in 1..12 || yearInt < 1900 || yearInt > Calendar.getInstance()
                .get(Calendar.YEAR)
        ) return false

        // Additional check for valid day of the month
        return try {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, dayInt)
            calendar.set(Calendar.MONTH, monthInt - 1)
            calendar.set(Calendar.YEAR, yearInt)
            calendar[Calendar.DAY_OF_MONTH] == dayInt && calendar[Calendar.MONTH] == monthInt - 1 && calendar[Calendar.YEAR] == yearInt
        } catch (e: Exception) {
            false
        }
    }
}