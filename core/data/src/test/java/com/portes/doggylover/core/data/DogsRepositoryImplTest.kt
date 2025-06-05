package com.portes.doggylover.core.data

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.data.datasource.DogsLocalDataSource
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSource
import com.portes.doggylover.core.data.mappers.entityToDomains
import com.portes.doggylover.core.testing.dogEntityData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DogsRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var remoteDataSource: DogsRemoteDataSource

    @Mock
    private lateinit var localDataSource: DogsLocalDataSource

    private val repository by lazy {
        DogsRepositoryImpl(
            remoteDataSource,
            localDataSource,
        )
    }

    @Test
    fun `validation set favorite dog`() =
        runTest {
            whenever(localDataSource.setFavoriteDog("Rex", 1)).thenReturn(
                true,
            )

            val result = repository.setFavoriteDog("Rex", 1)

            assertEquals(
                result,
                true,
            )
        }

    @Test
    fun `validation get dogs`() =
        runTest {
            whenever(localDataSource.getDogs()).thenReturn(
                flowOf(
                    dogEntityData.entityToDomains().filter { it.isFavorite },
                ),
            )

            val result = repository.getDogs(true)

            assertEquals(
                result.first().isSuccess,
                true,
            )
        }

    @Test
    fun `validation get favorites dogs`() =
        runTest {
            whenever(localDataSource.getFavoritesDogs()).thenReturn(
                flowOf(
                    dogEntityData.entityToDomains().filter { it.isFavorite },
                ),
            )

            val result = repository.getFavoritesDogs()

            assertEquals(
                result.first().size,
                1,
            )
        }
}
