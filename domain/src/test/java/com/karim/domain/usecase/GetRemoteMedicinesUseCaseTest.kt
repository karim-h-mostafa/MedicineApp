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

class GetRemoteMedicinesUseCaseTest {

    private val repository = mock(MedicineRepository::class.java)
    private val getRemoteMedicinesUseCase = GetRemoteMedicinesUseCase(repository)

    @Test
    fun `invoke returns flow of remote medicines`() = runTest {
        val medicines = listOf(
            Medicines.Medicine("Aspirin", "100mg", "Tablet"),
            Medicines.Medicine("Ciprofloxacin", "250mg", "Tablet")
        )
        val success = Result.Success(Medicines(medicines))
        `when`(repository.getRemoteMedicine()).thenReturn(flowOf(success))

        val result = getRemoteMedicinesUseCase.invoke()

        result.collect { actualMedicines ->
            assertEquals(success, actualMedicines)
        }
    }

    @Test
    fun `invoke returns flow of empty medicines`() = runTest {
        val success = Result.Success(Medicines(emptyList()))
        `when`(repository.getRemoteMedicine()).thenReturn(flowOf(success))

        val result = getRemoteMedicinesUseCase.invoke()

        result.collect { actualMedicines ->
            assertEquals(success, actualMedicines)
        }
    }

    @Test
    fun `invoke returns error`() = runTest {
        val error = Result.Error(Exception("Network error"))
        `when`(repository.getRemoteMedicine()).thenReturn(flowOf(error))

        val result = getRemoteMedicinesUseCase.invoke()

        result.collect { actualResult ->
            assertEquals(error, actualResult)
        }
    }
}
