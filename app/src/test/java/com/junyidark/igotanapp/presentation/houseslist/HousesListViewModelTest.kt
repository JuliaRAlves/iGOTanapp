package com.junyidark.igotanapp.presentation.houseslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetAllHousesListUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.GoToHouseDetailsState
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
class HousesListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val housesList = listOf(House(123, "Stark", emptyList()))

    @Mock
    private lateinit var getAllHousesListUseCase: GetAllHousesListUseCaseInterface

    @Mock
    private lateinit var getThemeUseCase: GetThemeUseCaseInterface

    @Mock
    private lateinit var setThemeUseCase: SetThemeUseCaseInterface

    private lateinit var viewModel: HousesListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = HousesListViewModel(
            getAllHousesListUseCase,
            getThemeUseCase,
            setThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN loadList AND load is error THEN should post error`() = runTest {
        // Configuration
        Mockito.`when`(getAllHousesListUseCase.invoke()).thenReturn(Result.failure(Exception()))

        // Execution
        viewModel.loadList()

        // Assertion
        Truth.assertThat(viewModel.isOnErrorLiveData.value).isTrue()
    }

    @Test
    fun `WHEN loadList AND load is success THEN should post list`() = runTest {
        // Configuration
        Mockito.`when`(getAllHousesListUseCase.invoke()).thenReturn(Result.success(housesList))

        // Execution
        viewModel.loadList()

        // Assertion
        Truth.assertThat(viewModel.housesListLiveData.value).isEqualTo(housesList)
    }

    @Test
    fun `WHEN onHouseClicked THEN should go to house details`() = runTest {
        // Configuration
        Mockito.`when`(getAllHousesListUseCase.invoke()).thenReturn(Result.success(housesList))

        // Execution
        viewModel.loadList()
        viewModel.onHouseClicked(0)

        // Assertion
        Truth.assertThat(viewModel.screenLiveData.value).isEqualTo(GoToHouseDetailsState(housesList[0]))
    }

    @Test
    fun `WHEN onSwitchThemeClicked THEN should update theme`() {
        // Configuration
        val theme = Theme.IRON_HAND
        Mockito.`when`(getThemeUseCase.invoke()).thenReturn(theme)

        // Execution
        viewModel.onSwitchThemeClicked()

        // Assertion
        Mockito.verify(setThemeUseCase).invoke()
        Truth.assertThat(viewModel.themeLiveData.value).isEqualTo(theme)
    }

    @Test
    fun `WHEN getTheme THEN should return theme`() {
        // Configuration
        val theme = Theme.IRON_HAND
        Mockito.`when`(getThemeUseCase.invoke()).thenReturn(theme)

        // Execution
        val result = viewModel.getTheme()

        // Assertion
        Truth.assertThat(result).isEqualTo(theme)
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