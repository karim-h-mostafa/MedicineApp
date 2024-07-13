package com.karim.data.model

import com.google.gson.annotations.SerializedName

data class MedicinesModel(
    val problems: List<Problem>
) {
    data class Problem(
        @SerializedName("Diabetes")
        val diabetes: List<Diabetes>
    ) {
        data class Diabetes(
            val medications: List<Medication>
        ) {

            data class Medication(
                @SerializedName("medicationsClasses")
                val medications: List<Medications>
            ) {
                data class Medications(
                    @SerializedName("className")
                    val medicines: List<Medicine>,
                    @SerializedName("className2")
                    val otherMedicines: List<Medicine>
                ) {
                    data class Medicine(
                        @SerializedName("associatedDrug")
                        val associatedDrugs: List<AssociatedDrug>,
                        @SerializedName("associatedDrug#2")
                        val otherAssociatedDrugs: List<AssociatedDrug>
                    ) {
                        data class AssociatedDrug(
                            val dose: String,
                            val name: String,
                            val strength: String
                        )
                    }
                }
            }
        }
    }
}