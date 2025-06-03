package com.portes.doggylover.core.data.mappers

import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.models.entity.DogEntity
import com.portes.doggylover.core.models.network.DogNetwork

fun DogEntity.entityToDomain() =
    Dog(
        name = name,
        description = description,
        age = age,
        image = image,
    )

fun DogNetwork.networkToEntity() =
    DogEntity(
        name = name,
        description = description,
        age = age,
        image = image,
    )

fun List<DogEntity>.entityToDomains(): List<Dog> = map(DogEntity::entityToDomain)

fun List<DogNetwork>.networkToEntities(): List<DogEntity> = map(DogNetwork::networkToEntity)
