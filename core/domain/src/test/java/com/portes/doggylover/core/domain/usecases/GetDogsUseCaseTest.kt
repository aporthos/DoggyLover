package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.domain.DogsRepository
import com.portes.doggylover.core.models.domain.Dog
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
class GetDogsUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: DogsRepository

    private val useCase by lazy {
        GetDogsUseCase(repository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `validation use case success`() =
        runTest {
            whenever(repository.getDogs(true)).thenReturn(flowOf(Result.success(dogDomainData)))
            val result = useCase(GetDogsUseCase.Params(true))
            assertEquals(result.first().isSuccess, true)
        }

    @Test
    fun `validation use case failed`() =
        runTest {
            whenever(repository.getDogs(true)).thenReturn(flowOf(Result.failure(Exception("Error"))))
            val result = useCase(GetDogsUseCase.Params(true))
            assertEquals(result.first().isFailure, true)
        }
}

val dogDomainData =
    listOf(
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
        Dog(
            name = "Ali Dorsey",
            description = "conclusionemque",
            age = 3170,
            image = "mediocrem",
            isFavorite = false,
        ),
    )
