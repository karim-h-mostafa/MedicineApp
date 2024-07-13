package com.karim.domain.usecase

import com.karim.domain.entity.Medicines
import com.karim.domain.repository.MedicineRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class StoreToRoomUseCaseTest {
    private val repository = mock(MedicineRepository::class.java)
    private val storeToRoomUseCase = StoreToRoomUseCase(repository)

    @Test
    fun `invoke stores medicine in repository`() = runTest {
        val medicine = Medicines.Medicine("Paracetamol", "500mg", "Tablet")
        val medicines = Medicines(listOf(medicine))
        storeToRoomUseCase.invoke(medicines)

        verify(repository).storeRemoteToRoom(medicines)
    }
}