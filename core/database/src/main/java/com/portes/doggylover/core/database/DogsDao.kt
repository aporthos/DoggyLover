package com.portes.doggylover.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.portes.doggylover.core.models.entity.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrIgnore(entities: List<DogEntity>)

    @Transaction
    @Query(
        value = """ 
            SELECT * FROM dogs 
    """,
    )
    fun getDogs(): Flow<List<DogEntity>>

    @Transaction
    @Query(
        value = """ 
            SELECT * FROM dogs
            WHERE isFavorite = :isFavorite
    """,
    )
    fun getFavoritesDogs(isFavorite: Int = 1): Flow<List<DogEntity>>

    @Query(
        value = """
            UPDATE dogs SET isFavorite = :isFavorite 
            WHERE name = :name
    """,
    )
    suspend fun updateFavoriteDog(
        isFavorite: Int,
        name: String,
    ): Int
}
