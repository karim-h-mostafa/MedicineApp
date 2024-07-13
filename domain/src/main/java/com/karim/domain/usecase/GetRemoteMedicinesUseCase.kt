package com.karim.domain.usecase

import com.karim.domain.repository.MedicineRepository

class GetRemoteMedicinesUseCase(private val medicineRepository: MedicineRepository) {
    operator fun invoke() = medicineRepository.getRemoteMedicine()
}