package com.portes.doggylover.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.portes.doggylover.core.models.entity.DogEntity

@Database(
    entities = [
        DogEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class DoggyLoverDatabase : RoomDatabase() {
    abstract val dogsDao: DogsDao
}
