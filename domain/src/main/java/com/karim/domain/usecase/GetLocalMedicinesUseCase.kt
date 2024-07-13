package com.karim.domain.usecase

import com.karim.domain.repository.MedicineRepository

class GetLocalMedicinesUseCase(private val medicineRepository: MedicineRepository) {
    operator fun invoke() = medicineRepository.getLocalMedicine()
}