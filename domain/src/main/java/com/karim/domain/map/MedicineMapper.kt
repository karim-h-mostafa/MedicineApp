package com.karim.domain.map

interface MedicineMapper<Model, Entity> {
    fun fromModelToEntity(model: Model): Entity
    fun fromEntityToModel(entity: Entity): Model
    fun fromModelListToEntityList(modelList: List<Model>): List<Entity> {
        return modelList.map { fromModelToEntity(it) }
    }

    fun fromEntityListToModelList(entityList: List<Entity>): List<Model> {
        return entityList.map { fromEntityToModel(it) }
    }

}