package com.portes.doggylover.core.database.di

import android.content.Context
import androidx.room.Room
import com.portes.doggylover.core.database.DoggyLoverDatabase
import com.portes.doggylover.core.database.DogsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB_NAME = "DoggyLoverDatabase.db"

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): DoggyLoverDatabase =
        Room
            .databaseBuilder(
                context = context,
                DoggyLoverDatabase::class.java,
                DB_NAME,
            ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDogsDao(database: DoggyLoverDatabase): DogsDao = database.dogsDao
}
