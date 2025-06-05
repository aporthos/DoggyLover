package com.portes.doggylover.core.data.datasource

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.data.ApiServices
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DogsRemoteDataSourceImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var service: ApiServices

    private val dataSource by lazy {
        DogsRemoteDataSourceImpl(
            service,
            mainDispatcherRule.testDispatcher,
        )
    }

    @Test
    fun `validation get events success`() =
        runTest {
            val result = dataSource.getDogs()
            assertEquals(result.isSuccess, true)
        }

    @Test
    fun `validation get events failed`() =
        runTest {
            whenever(service.getDogs()).thenThrow(RuntimeException())
            val result = dataSource.getDogs()
            assertEquals(result.isFailure, true)
        }
}
