package com.junyidark.igotanapp.presentation.characterslist

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetAllCharactersListUseCase
import com.junyidark.igotanapp.domain.usecases.GetCharacterFullInfoUseCase
import com.junyidark.igotanapp.presentation.characterslist.CharactersListActivity.Companion.EXTRA_CHARACTERS_RESULT_LIST
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllCharactersListUseCase: GetAllCharactersListUseCase,
    private val getCharacterFullInfoUseCase: GetCharacterFullInfoUseCase
) : ViewModel() {

    private val screenMutableLiveData = MutableLiveData<CharactersListViewState>()
    val screenLiveData: LiveData<CharactersListViewState> = screenMutableLiveData

    private val charactersResultList by lazy {
        savedStateHandle.get<List<CharacterBasics>>(
            EXTRA_CHARACTERS_RESULT_LIST
        ) ?: emptyList<CharacterBasics>()
    }

    fun loadList() {
        if (charactersResultList.isEmpty()) {
            loadAllCharacters()
        } else {
            screenMutableLiveData.value = LoadedListState(charactersResultList)
        }
    }

    private fun loadAllCharacters() {
        viewModelScope.launch {
            val list = getAllCharactersListUseCase.invoke()
            screenMutableLiveData.postValue(LoadedListState(list))
        }
    }

    fun onCharacterClicked(position: Int) {
        viewModelScope.launch {
            val characterDetails = getCharacterFullInfoUseCase.invoke(charactersResultList[position])
            screenMutableLiveData.postValue(GoToCharacterDetailsState(characterDetails))
        }
    }

    fun onMenuClicked() {
        screenMutableLiveData.value = OpenMenuState
    }

    fun onBackPressed() {
        screenMutableLiveData.value = GoToHomeState
    }

    sealed class CharactersListViewState {
        data class LoadedListState(val list: List<CharacterBasics>) : CharactersListViewState()
        data class GoToCharacterDetailsState(val character: Character) : CharactersListViewState()
        object GoToHomeState : CharactersListViewState()
        object OpenMenuState : CharactersListViewState()
    }
}