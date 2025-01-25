package com.junyidark.igotanapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCaseInterface
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToAllCharactersState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToAuthorState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToTheHousesState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.LoadedResultState
import com.junyidark.igotanapp.presentation.theme.Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchCharacterByNameUseCase: SearchCharacterByNameUseCaseInterface

    @Mock
    private lateinit var getThemeUseCase: GetThemeUseCaseInterface

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = HomeViewModel(
            searchCharacterByNameUseCase,
            getThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN onSearchClicked AND search is error THEN should post error`() = runTest {
        // Configuration
        val query = "jon"
        Mockito.`when`(searchCharacterByNameUseCase.invoke(query)).thenReturn(Result.failure(Exception()))

        // Execution
        viewModel.onTextChanged(query)
        viewModel.onSearchClicked()

        // Assertion
        Truth.assertThat(viewModel.isOnErrorLiveData.value).isTrue()
    }

    @Test
    fun `WHEN onSearchClicked AND search is success THEN should post result`() = runTest {
        // Configuration
        val query = "jon"
        Mockito.`when`(searchCharacterByNameUseCase.invoke(query)).thenReturn(Result.success(emptyList()))

        // Execution
        viewModel.onTextChanged(query)
        viewModel.onSearchClicked()

        // Assertion
        Truth.assertThat(viewModel.screenLiveData.value).isEqualTo(LoadedResultState(emptyList()))
    }

    @Test
    fun `WHEN onAllCharactersClicked THEN should go to all characters`() {
        // Execution
        viewModel.onAllCharactersClicked()

        // Assertion
        Truth.assertThat(viewModel.screenLiveData.value).isEqualTo(GoToAllCharactersState)
    }

    @Test
    fun `WHEN onTheHousesClicked THEN should go to houses`() {
        // Execution
        viewModel.onTheHousesClicked()

        // Assertion
        Truth.assertThat(viewModel.screenLiveData.value).isEqualTo(GoToTheHousesState)
    }

    @Test
    fun `WHEN onAuthorNameClicked THEN should go to author`() {
        // Execution
        viewModel.onAuthorNameClicked()

        // Assertion
        Truth.assertThat(viewModel.screenLiveData.value).isEqualTo(GoToAuthorState)
    }

    @Test
    fun `WHEN onTextChanged THEN should update query`() {
        // Execution
        viewModel.onTextChanged("text")

        // Assertion
        Truth.assertThat(viewModel.queryLiveData.value).isEqualTo("text")
    }

    @Test
    fun `WHEN updateTheme THEN should update theme`() {
        // Configuration
        val theme = Theme.IRON_HAND
        Mockito.`when`(getThemeUseCase.invoke()).thenReturn(theme)

        // Execution
        viewModel.updateTheme()

        // Assertion
        Truth.assertThat(viewModel.themeLiveData.value).isEqualTo(theme)
    }
}