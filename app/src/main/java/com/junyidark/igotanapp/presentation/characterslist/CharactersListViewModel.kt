package com.junyidark.igotanapp.presentation.characterslist

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetAllCharactersListUseCase
import com.junyidark.igotanapp.presentation.characterslist.CharactersListActivity.Companion.EXTRA_CHARACTERS_RESULT_LIST
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListNavigationViewState.GoToCharacterDetailsState
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListNavigationViewState.OpenMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllCharactersListUseCase: GetAllCharactersListUseCase
) : ViewModel() {

    private val charactersListMutableLiveData = MutableLiveData<List<CharacterBasics>>()
    val charactersListLiveData: LiveData<List<CharacterBasics>> = charactersListMutableLiveData

    private val navigationMutableLiveData = MutableLiveData<CharactersListNavigationViewState>()
    val navigationLiveData: LiveData<CharactersListNavigationViewState> = navigationMutableLiveData


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

    fun onMenuClicked() {
        navigationMutableLiveData.value = OpenMenuState
    }

    sealed class CharactersListNavigationViewState {
        data class GoToCharacterDetailsState(val character: CharacterBasics) : CharactersListNavigationViewState()
        object OpenMenuState : CharactersListNavigationViewState()
    }
}