package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.domain.DogsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class UpdateFavoriteDogUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: DogsRepository

    private val useCase by lazy {
        UpdateFavoriteDogUseCase(repository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `validation use case success`() =
        runTest {
            whenever(repository.setFavoriteDog("Rex", 1)).thenReturn(true)
            val result = useCase(UpdateFavoriteDogUseCase.Params("Rex", true))
            assertEquals(result.isSuccess, true)
        }

    @Test
    fun `validation use case failed`() =
        runTest {
            whenever(repository.setFavoriteDog("Rex", 1)).thenReturn(false)
            val result = useCase(UpdateFavoriteDogUseCase.Params("Rex", true))
            assertEquals(result.isFailure, true)
        }
}
