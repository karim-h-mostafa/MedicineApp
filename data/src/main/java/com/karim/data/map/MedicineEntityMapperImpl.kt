package com.karim.data.map

import com.karim.data.model.MedicinesModel
import com.karim.data.repository.local.entity.MedicineEntity
import com.karim.domain.entity.Medicines
import com.karim.domain.map.MedicineMapper

class MedicineEntityMapperImpl : MedicineMapper<Medicines.Medicine, MedicineEntity> {
    override fun fromModelToEntity(model: Medicines.Medicine): MedicineEntity {
        return MedicineEntity(
            name = model.name,
            dose = model.dose,
            strength = model.strength
        )
    }

    override fun fromEntityToModel(entity: MedicineEntity): Medicines.Medicine {
        return Medicines.Medicine(
            name = entity.name,
            dose = entity.dose,
            strength = entity.strength
        )
    }


}