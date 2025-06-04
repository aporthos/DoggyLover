package com.portes.doggylover.feature.onboarding

import android.content.res.Resources
import javax.inject.Inject

class OnboardingResourcesManagerImpl
    @Inject
    constructor(
        private val resources: Resources,
    ) : OnboardingResourcesManager {
        override val titleDogs: String
            get() = resources.getString(R.string.title_dogs)
        override val messageDogs: String
            get() = resources.getString(R.string.message_dogs)
        override val titleFavorites: String
            get() = resources.getString(R.string.title_favorites)
        override val messageFavorites: String
            get() = resources.getString(R.string.message_favorites)
    }

interface OnboardingResourcesManager {
    val titleDogs: String
    val messageDogs: String
    val titleFavorites: String
    val messageFavorites: String
}
