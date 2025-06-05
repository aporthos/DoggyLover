package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.domain.DogsRepository
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
class GetFavoritesDogsUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: DogsRepository

    private val useCase by lazy {
        GetFavoritesDogsUseCase(repository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `validation use case success`() =
        runTest {
            whenever(repository.getFavoritesDogs()).thenReturn(flowOf(dogDomainData))
            val result = useCase(Unit)
            assertEquals(result.first().size, 3)
        }
}
