package com.karim.domain.entity


data class Medicines(
    val medicines: List<Medicine>
) {
    data class Medicine(
        val name: String,
        val dose: String,
        val strength: String
    )
}