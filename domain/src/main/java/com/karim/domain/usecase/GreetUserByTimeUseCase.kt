package com.karim.domain.usecase

import java.util.Calendar

class GreetUserByTimeUseCase {
    operator fun invoke(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return "Good ${
            when (hour) {
                in 6..11 -> "Morning"
                in 12..17 -> "Afternoon"
                else -> "Evening"
            }
        }"
    }
}