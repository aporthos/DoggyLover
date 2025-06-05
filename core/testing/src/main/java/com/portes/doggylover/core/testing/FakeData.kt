package com.portes.doggylover.core.testing

import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.models.entity.DogEntity

val dogDomainData =
    listOf(
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
    )

val dogEntityData =
    listOf(
        DogEntity(
            name = "Evangelina Rich",
            description = "sagittis",
            age = 6542,
            image = "usu",
            isFavorite = false,
        ),
        DogEntity(
            name = "Evangelina Rich",
            description = "sagittis",
            age = 6542,
            image = "usu",
            isFavorite = false,
        ),
        DogEntity(
            name = "Evangelina Rich",
            description = "sagittis",
            age = 6542,
            image = "usu",
            isFavorite = true,
        ),
    )
