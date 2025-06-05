package com.portes.doggylover.feature.favorites

import app.cash.turbine.test
import com.portes.doggylover.core.common.MainDispatcherRule
import com.portes.doggylover.core.domain.usecases.GetFavoritesDogsUseCase
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
class FavoritesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeDogsRepository()

    private val getFavoritesDogsUseCase =
        GetFavoritesDogsUseCase(repository, mainDispatcherRule.testDispatcher)

    private val updateFavoriteDogUseCase =
        UpdateFavoriteDogUseCase(repository, mainDispatcherRule.testDispatcher)

    private lateinit var viewModel: FavoritesViewModel

    @Before
    fun setUp() {
        viewModel = FavoritesViewModel(getFavoritesDogsUseCase, updateFavoriteDogUseCase)
    }

    @Test
    fun `validation loading dogs`() =
        runTest {
            viewModel.dogsState.test {
                assertEquals(FavoritesUiState.Loading, awaitItem())
                repository.addDogs(Result.success(dogDomainData))
                assertEquals(FavoritesUiState.Items(dogDomainData.domainToUis()), awaitItem())
            }
        }
}
