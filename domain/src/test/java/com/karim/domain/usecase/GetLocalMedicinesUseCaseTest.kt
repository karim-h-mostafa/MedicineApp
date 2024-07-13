package com.karim.domain.usecase

import com.karim.core.util.network.Result
import com.karim.domain.entity.Medicines
import com.karim.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetLocalMedicinesUseCaseTest {
    private val repository = mock(MedicineRepository::class.java)
    private val getLocalMedicinesUseCase = GetLocalMedicinesUseCase(repository)

    @Test
    fun `invoke returns flow of local medicines`() = runTest {
        val medicines = listOf(
            Medicines.Medicine("Paracetamol", "500mg", "Tablet"),
            Medicines.Medicine("Ibuprofen", "200mg", "Tablet")
        )
        val success = Result.Success(Medicines(medicines))
        `when`(repository.getLocalMedicine()).thenReturn(flowOf(success))

        val result = getLocalMedicinesUseCase.invoke()

        result.collect { actualMedicines ->
            assertEquals(success, actualMedicines)
        }
    }

    @Test
    fun `invoke returns flow of empty medicines`() = runTest {
        val success = Result.Success(Medicines(emptyList()))
        `when`(repository.getLocalMedicine()).thenReturn(flowOf(success))

        val result = getLocalMedicinesUseCase.invoke()

        result.collect { actualMedicines ->
            assertEquals(success, actualMedicines)
        }
    }

    @Test
    fun `invoke returns error`() = runTest {
        val error = Result.Error(Exception("Database error"))
        `when`(repository.getLocalMedicine()).thenReturn(flowOf(error))

        val result = getLocalMedicinesUseCase.invoke()

        result.collect { actualResult ->
            assertEquals(error, actualResult)
        }
    }
}
