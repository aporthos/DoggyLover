package com.portes.doggylover.feature.dogs

import app.cash.turbine.test
import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.domain.usecases.GetDogsUseCase
import com.portes.doggylover.core.domain.usecases.UpdateFavoriteDogUseCase
import com.portes.doggylover.core.models.ui.domainToUis
import com.portes.doggylover.core.testing.FakeDogsRepository
import com.portes.doggylover.core.testing.dogDomainData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DogsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeDogsRepository()

    private val getDogsUseCase = GetDogsUseCase(repository, mainDispatcherRule.testDispatcher)

    private val updateFavoriteDogUseCase =
        UpdateFavoriteDogUseCase(repository, mainDispatcherRule.testDispatcher)

    private lateinit var viewModel: DogsViewModel

    @Before
    fun setUp() {
        viewModel = DogsViewModel(getDogsUseCase, updateFavoriteDogUseCase)
    }

    @Test
    fun `validation loading dogs`() =
        runTest {
            viewModel.dogsState.test {
                assertEquals(DogsUiState.Loading, awaitItem())
                repository.addDogs(Result.success(dogDomainData))
                assertEquals(DogsUiState.Items(false, dogDomainData.domainToUis()), awaitItem())
            }
        }
}
