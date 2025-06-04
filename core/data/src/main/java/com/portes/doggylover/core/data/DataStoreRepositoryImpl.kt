package com.portes.doggylover.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.portes.doggylover.core.data.di.DataModule
import com.portes.doggylover.core.domain.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DataStoreRepositoryImpl
    @Inject
    constructor(
        @Named(DataModule.DOGGY_DATASTORE) private val dataStore: DataStore<Preferences>,
    ) : DataStoreRepository {
        companion object {
            private val KEY_SHOW_ONBOARDING = booleanPreferencesKey("SHOW_ONBOARDING")
        }

        override suspend fun completeOnboarding() {
            dataStore.edit { preferences ->
                preferences[KEY_SHOW_ONBOARDING] = false
            }
        }

        override val canShowOnboardingStream: Flow<Boolean>
            get() =
                dataStore.data.map { preferences ->
                    preferences[KEY_SHOW_ONBOARDING] ?: true
                }
    }
