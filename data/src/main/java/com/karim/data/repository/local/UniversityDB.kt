package com.karim.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karim.data.repository.local.dao.MedicineDao
import com.karim.data.repository.local.entity.MedicineEntity

@Database(
    entities = [
        MedicineEntity::class
    ], version = 1, exportSchema = false
)

abstract class MedicineDB : RoomDatabase() {
    abstract fun getMedicineDao(): MedicineDao
}