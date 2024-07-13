package com.karim.data.map

import com.karim.data.model.MedicinesModel
import com.karim.domain.entity.Medicines
import com.karim.domain.map.MedicineMapper

class MedicineModelMapperImpl : MedicineMapper<MedicinesModel, Medicines> {
    override fun fromModelToEntity(model: MedicinesModel): Medicines {
        val medicineList = mutableListOf<Medicines.Medicine>()
        model.problems.map { problem ->
            problem.diabetes.map { diabetes ->
                diabetes.medications.map { medication ->
                    medication.medications.map { medications ->
                        medications.medicines.map { medicine ->
                            medicineList += medicine.associatedDrugs.map { associatedDrug ->
                                Medicines.Medicine(
                                    associatedDrug.name,
                                    associatedDrug.dose,
                                    associatedDrug.strength
                                )
                            }
                            medicineList += medicine.otherAssociatedDrugs.map { associatedDrug ->
                                Medicines.Medicine(
                                    associatedDrug.name,
                                    associatedDrug.dose,
                                    associatedDrug.strength
                                )
                            }
                        }
                        medications.otherMedicines.map { medicine ->
                            medicineList += medicine.associatedDrugs.map { associatedDrug ->
                                Medicines.Medicine(
                                    associatedDrug.name,
                                    associatedDrug.dose,
                                    associatedDrug.strength
                                )
                            }
                            medicineList += medicine.otherAssociatedDrugs.map { associatedDrug ->
                                Medicines.Medicine(
                                    associatedDrug.name,
                                    associatedDrug.dose,
                                    associatedDrug.strength
                                )
                            }
                        }
                    }
                }
            }
        }
        return Medicines(medicineList)
    }

    override fun fromEntityToModel(entity: Medicines): MedicinesModel {
        TODO("Not yet implemented")
    }
}