package com.karim.data.repository

import android.util.Log
import com.karim.core.util.network.NetworkResponse
import com.karim.core.util.network.Result
import com.karim.data.model.MedicinesModel
import com.karim.data.repository.local.dao.MedicineDao
import com.karim.data.repository.local.entity.MedicineEntity
import com.karim.data.repository.remote.MedicineAPI
import com.karim.domain.entity.Medicines
import com.karim.domain.map.MedicineMapper
import com.karim.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

class MedicineRepositoryImpl(
    private val api: MedicineAPI,
    private val medicineDao: MedicineDao,
    private val medicineModelToEntityMapper: MedicineMapper<MedicinesModel, Medicines>,
    private val medicineEntityToModelMapper: MedicineMapper<Medicines.Medicine, MedicineEntity>
) : MedicineRepository {
    override fun getRemoteMedicine() = flow<Result<Medicines>> {
        when (val result = api.getMedicine()) {
            is NetworkResponse.Success -> {
                emit(Result.Success(medicineModelToEntityMapper.fromModelToEntity(result.body)))
            }

            is NetworkResponse.NetworkError -> {
                throw Exception("Network Error")
            }

            is NetworkResponse.ApiError -> {
                Log.d("MedicineRepositoryImpl", "getMedicine: ${result.body.message} ")
                throw Exception("Server Error")
            }

            is NetworkResponse.UnknownError -> {
                throw Exception("Unknown Error")
            }

        }
    }.catch { exception ->
        emit(Result.Error(Exception(exception.message)))
    }

    override suspend fun storeRemoteToRoom(medicines: Medicines) {
        medicineDao.insertAll(medicineEntityToModelMapper.fromModelListToEntityList(medicines.medicines))
    }

    override fun getLocalMedicine() = flow {
        val medicines = medicineDao.getAll()
        if (medicines.isEmpty()) {
            emit(Result.Error(Exception("no local medicine")))
        } else {
            emit(
                Result.Success(
                    Medicines(
                        medicineEntityToModelMapper.fromEntityListToModelList(
                            medicines
                        )
                    )
                )
            )
        }
    }.catch { exception ->
        emit(Result.Error(Exception(exception)))
    }

}