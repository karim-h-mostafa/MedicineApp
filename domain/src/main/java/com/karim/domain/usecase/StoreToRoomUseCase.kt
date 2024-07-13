package com.karim.domain.usecase

import com.karim.domain.entity.Medicines
import com.karim.domain.repository.MedicineRepository

class StoreToRoomUseCase(private val medicineRepository: MedicineRepository) {
    suspend operator fun invoke(medicines: Medicines) = medicineRepository.storeRemoteToRoom(medicines)
}