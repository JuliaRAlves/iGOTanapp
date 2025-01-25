package com.junyidark.igotanapp.presentation.housedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.theme.Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
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
class HouseDetailsViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val house = House(123, "Stark", emptyList())

    private val savedStateHandle = SavedStateHandle().apply {
        set("EXTRA_HOUSE", house)
    }

    @Mock
    private lateinit var getThemeUseCase: GetThemeUseCaseInterface

    @Mock
    private lateinit var setThemeUseCase: SetThemeUseCaseInterface

    private lateinit var viewModel: HouseDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = HouseDetailsViewModel(
            savedStateHandle,
            getThemeUseCase,
            setThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN loadInfo AND list is empty AND load is error THEN should post error`() {
        // Execution
        viewModel.loadInfo()

        // Assertion
        Truth.assertThat(viewModel.houseDetailsLiveData.value).isEqualTo(house)
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