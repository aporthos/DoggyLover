package com.portes.doggylover.core.models.ui

import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.designsystem.R as Designsystem

data class DogUi(
    val name: String,
    val description: String,
    val age: Int,
    val image: String,
    val isFavorite: Boolean,
    val iconFavorite: Int,
)

fun Dog.domainToUi() =
    DogUi(
        name = name,
        description = description,
        age = age,
        image = image,
        isFavorite = isFavorite,
        iconFavorite = getImageFavorite(isFavorite),
    )

fun List<Dog>.domainToUis(): List<DogUi> = map(Dog::domainToUi)

private fun getImageFavorite(isFavorite: Boolean): Int =
    if (isFavorite) {
        Designsystem.drawable.favorite_selected
    } else {
        Designsystem.drawable.favorite_unselected
    }
