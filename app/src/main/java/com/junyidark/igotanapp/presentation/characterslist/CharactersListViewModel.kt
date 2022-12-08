package com.junyidark.igotanapp.presentation.characterslist

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetAllCharactersListUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.characterslist.CharactersListActivity.Companion.EXTRA_CHARACTERS_RESULT_LIST
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListNavigationViewState.GoToCharacterDetailsState
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllCharactersListUseCase: GetAllCharactersListUseCaseInterface,
    private val getThemeUseCase: GetThemeUseCaseInterface,
    private val setThemeUseCase: SetThemeUseCaseInterface
) : ViewModel() {

    private val charactersListMutableLiveData = MutableLiveData<List<CharacterBasics>>()
    val charactersListLiveData: LiveData<List<CharacterBasics>> = charactersListMutableLiveData

    private val navigationMutableLiveData = MutableLiveData<CharactersListNavigationViewState>()
    val navigationLiveData: LiveData<CharactersListNavigationViewState> = navigationMutableLiveData

    private val themeMutableLiveData = MutableLiveData<Theme>()
    val themeLiveData: LiveData<Theme> = themeMutableLiveData


    private val charactersResultList by lazy {
        savedStateHandle.get<List<CharacterBasics>>(
            EXTRA_CHARACTERS_RESULT_LIST
        ) ?: emptyList<CharacterBasics>()
    }

    private var charactersList = emptyList<CharacterBasics>()

    fun loadList() {
        if (charactersResultList.isEmpty()) {
            loadAllCharacters()
        } else {
            charactersListMutableLiveData.value = charactersResultList
        }
    }

    private fun loadAllCharacters() {
        viewModelScope.launch {
            getAllCharactersListUseCase.invoke(onSuccess = { list ->
                charactersListMutableLiveData.postValue(list)
                charactersList = list
            })
        }
    }

    fun onCharacterClicked(position: Int) {
        navigationMutableLiveData.postValue(GoToCharacterDetailsState(charactersList[position]))
    }

    fun onSwitchThemeClicked() {
        setThemeUseCase.invoke()
        themeMutableLiveData.value = getThemeUseCase.invoke()
    }

    fun getTheme(): Theme {
        return getThemeUseCase.invoke()
    }

    fun updateTheme() {
        themeMutableLiveData.value = getThemeUseCase.invoke()
    }

    sealed class CharactersListNavigationViewState {
        data class GoToCharacterDetailsState(val character: CharacterBasics) : CharactersListNavigationViewState()
    }
}