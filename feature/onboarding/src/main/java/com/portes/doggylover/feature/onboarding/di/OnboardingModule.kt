package com.portes.doggylover.feature.onboarding.di

import com.portes.doggylover.feature.onboarding.OnboardingResourcesManager
import com.portes.doggylover.feature.onboarding.OnboardingResourcesManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OnboardingModule {
    @Binds
    fun bindsResourcesManager(manager: OnboardingResourcesManagerImpl): OnboardingResourcesManager
}
