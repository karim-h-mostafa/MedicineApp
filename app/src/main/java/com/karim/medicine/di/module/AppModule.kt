package com.karim.medicine.di.module

import com.karim.domain.repository.MedicineRepository
import com.karim.domain.usecase.GetLocalMedicinesUseCase
import com.karim.domain.usecase.GetRemoteMedicinesUseCase
import com.karim.domain.usecase.GreetUserByTimeUseCase
import com.karim.domain.usecase.StoreToRoomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideGreetUserByTimeUseCase() = GreetUserByTimeUseCase()

    @Provides
    fun provideGetMedicinesUseCase(medicineRepository: MedicineRepository) =
        GetRemoteMedicinesUseCase(medicineRepository)

    @Provides
    fun provideGetLocalMedicinesUseCase(medicineRepository: MedicineRepository) = GetLocalMedicinesUseCase(medicineRepository)

    @Provides
    fun provideStoreToRoomUSeCase(medicineRepository: MedicineRepository) = StoreToRoomUseCase(medicineRepository)

}