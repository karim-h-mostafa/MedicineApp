package com.karim.domain.repository

import com.karim.core.util.network.Result
import com.karim.domain.entity.Medicines
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    fun getRemoteMedicine(): Flow<Result<Medicines>>
    suspend fun storeRemoteToRoom(medicines: Medicines)
    fun getLocalMedicine(): Flow<Result<Medicines>>

}