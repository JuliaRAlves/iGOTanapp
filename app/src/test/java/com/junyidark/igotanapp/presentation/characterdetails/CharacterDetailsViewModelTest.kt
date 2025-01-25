package com.junyidark.igotanapp.presentation.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetCharacterFullInfoUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsActionsViewState.CopiedQuoteState
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
class CharacterDetailsViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val characterBasics = CharacterBasics("url", "Jon Snow", "King of the North", "Stark")

    private val savedStateHandle = SavedStateHandle().apply {
        set("EXTRA_CHARACTER_BASICS", characterBasics)
    }

    @Mock
    private lateinit var getCharacterFullInfoUseCase: GetCharacterFullInfoUseCaseInterface

    @Mock
    private lateinit var getThemeUseCase: GetThemeUseCaseInterface

    @Mock
    private lateinit var setThemeUseCase: SetThemeUseCaseInterface

    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = CharacterDetailsViewModel(
            savedStateHandle,
            getCharacterFullInfoUseCase,
            getThemeUseCase,
            setThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN loadInfo AND get full info is error THEN should post error`() = runTest {
        // Configuration
        Mockito.`when`(getCharacterFullInfoUseCase.invoke(characterBasics)).thenReturn(Result.failure(Exception()))

        // Execution
        viewModel.loadInfo()

        // Assertion
        Truth.assertThat(viewModel.isOnErrorLiveData.value).isTrue()
    }

    @Test
    fun `WHEN loadInfo AND get full info is success THEN should post details`() = runTest {
        // Configuration
        val character = Mockito.mock(Character::class.java)
        Mockito.`when`(getCharacterFullInfoUseCase.invoke(characterBasics)).thenReturn(Result.success(character))

        // Execution
        viewModel.loadInfo()

        // Assertion
        Truth.assertThat(viewModel.characterDetailsLiveData.value).isEqualTo(character)
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

    @Test
    fun `WHEN onQuoteCopied THEN should update theme`() {
        // Configuration
        val quote = "Winter is coming"

        // Execution
        viewModel.onQuoteCopied(quote)

        // Assertion
        Truth.assertThat(viewModel.actionsLiveData.value).isEqualTo(CopiedQuoteState("Quote", quote))
    }
}