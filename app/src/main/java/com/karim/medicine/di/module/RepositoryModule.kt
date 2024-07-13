package com.karim.medicine.di.module

import android.content.Context
import androidx.room.Room
import com.karim.data.map.MedicineEntityMapperImpl
import com.karim.data.map.MedicineModelMapperImpl
import com.karim.data.model.MedicinesModel
import com.karim.data.repository.MedicineRepositoryImpl
import com.karim.data.repository.local.MedicineDB
import com.karim.data.repository.local.dao.MedicineDao
import com.karim.data.repository.local.entity.MedicineEntity
import com.karim.data.repository.remote.MedicineAPI
import com.karim.domain.entity.Medicines
import com.karim.domain.map.MedicineMapper
import com.karim.domain.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun apiService(retrofit: Retrofit): MedicineAPI {
        return retrofit.create(MedicineAPI::class.java)
    }

    @Provides
    fun provideMedicineRepository(
        api: MedicineAPI,
        medicineModelToEntityMapper: MedicineMapper<MedicinesModel, Medicines>,
        medicineEntityToModelMapper: MedicineMapper<Medicines.Medicine, MedicineEntity>,
        medicineDao: MedicineDao,

        ): MedicineRepository {
        return MedicineRepositoryImpl(
            api = api,
            medicineDao = medicineDao,
            medicineModelToEntityMapper = medicineModelToEntityMapper,
            medicineEntityToModelMapper = medicineEntityToModelMapper
        )
    }

    @Provides
    fun provideMedicineModelToEntityMapper(): MedicineMapper<MedicinesModel, Medicines> =
        MedicineModelMapperImpl()

    @Provides
    fun provideMedicineEntityToModelMapper(): MedicineMapper<Medicines.Medicine, MedicineEntity> =
        MedicineEntityMapperImpl()


    @Singleton
    @Provides
    fun provideMedicineRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MedicineDB::class.java, "medicine_db").build()

    @Singleton
    @Provides
    fun provideMedicineDao(medicineDB: MedicineDB) = medicineDB.getMedicineDao()


}