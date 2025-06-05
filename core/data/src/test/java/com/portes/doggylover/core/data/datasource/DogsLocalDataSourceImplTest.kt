package com.portes.doggylover.core.data.datasource

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.database.DogsDao
import com.portes.doggylover.core.testing.dogEntityData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DogsLocalDataSourceImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var dogsDao: DogsDao

    private val dataSource by lazy {
        DogsLocalDataSourceImpl(
            dogsDao,
            mainDispatcherRule.testDispatcher,
        )
    }

    @Test
    fun `validation get all dogs`() =
        runTest {
            whenever(dogsDao.getDogs()).thenReturn(
                flowOf(
                    dogEntityData,
                ),
            )

            val result = dataSource.getDogs()
            assertEquals(result.first().size, 3)
        }

    @Test
    fun `validation get favorites dogs`() =
        runTest {
            whenever(dogsDao.getFavoritesDogs()).thenReturn(
                flowOf(
                    dogEntityData.filter { it.isFavorite },
                ),
            )

            val result = dataSource.getFavoritesDogs()
            assertEquals(result.first().size, 1)
        }

    @Test
    fun `validation set favorite dog`() =
        runTest {
            whenever(
                dogsDao.updateFavoriteDog(1, "Rex"),
            ).thenReturn(1)
            val result = dataSource.setFavoriteDog("Rex", 1)
            assertEquals(result, true)
        }
}
