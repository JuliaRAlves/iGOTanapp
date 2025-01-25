package com.junyidark.igotanapp.presentation.characterslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetAllCharactersListUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListNavigationViewState.GoToCharacterDetailsState
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
class CharactersListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val characterBasicsList = listOf(CharacterBasics("url", "Jon Snow", "King of the North", "Stark"))

    private val savedStateHandle = SavedStateHandle()

    @Mock
    private lateinit var getAllCharactersListUseCase: GetAllCharactersListUseCaseInterface

    @Mock
    private lateinit var getThemeUseCase: GetThemeUseCaseInterface

    @Mock
    private lateinit var setThemeUseCase: SetThemeUseCaseInterface

    private lateinit var viewModel: CharactersListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = CharactersListViewModel(
            savedStateHandle,
            getAllCharactersListUseCase,
            getThemeUseCase,
            setThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN loadList AND list is empty AND load is error THEN should post error`() = runTest {
        // Configuration
        savedStateHandle["EXTRA_CHARACTERS_RESULT_LIST"] = emptyList<CharacterBasics>()
        Mockito.`when`(getAllCharactersListUseCase.invoke()).thenReturn(Result.failure(Exception()))

        // Execution
        viewModel.loadList()

        // Assertion
        Truth.assertThat(viewModel.isOnErrorLiveData.value).isTrue()
    }

    @Test
    fun `WHEN loadList AND list is empty AND load is success THEN should post list`() = runTest {
        // Configuration
        savedStateHandle["EXTRA_CHARACTERS_RESULT_LIST"] = emptyList<CharacterBasics>()
        Mockito.`when`(getAllCharactersListUseCase.invoke()).thenReturn(Result.success(characterBasicsList))

        // Execution
        viewModel.loadList()

        // Assertion
        Truth.assertThat(viewModel.charactersListLiveData.value).isEqualTo(characterBasicsList)
    }

    @Test
    fun `WHEN loadList AND list is not empty THEN should post list`() {
        // Configuration
        savedStateHandle["EXTRA_CHARACTERS_RESULT_LIST"] = characterBasicsList

        // Execution
        viewModel.loadList()

        // Assertion
        Truth.assertThat(viewModel.charactersListLiveData.value).isEqualTo(characterBasicsList)
    }

    @Test
    fun `WHEN onCharacterClicked THEN should go to character details`() {
        // Configuration
        savedStateHandle["EXTRA_CHARACTERS_RESULT_LIST"] = characterBasicsList

        // Execution
        viewModel.loadList()
        viewModel.onCharacterClicked(0)

        // Assertion
        Truth.assertThat(viewModel.navigationLiveData.value)
            .isEqualTo(GoToCharacterDetailsState(characterBasicsList[0]))
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