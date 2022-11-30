package com.junyidark.igotanapp.presentation.characterdetails

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetCharacterFullInfoUseCaseInterface
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsActivity.Companion.EXTRA_CHARACTER_BASICS
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsActionsViewState.CopiedQuoteState
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsNavigationViewState.OpenMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterFullInfoUseCase: GetCharacterFullInfoUseCaseInterface
) : ViewModel() {

    private val characterDetailsMutableLiveData = MutableLiveData<Character>()
    val characterDetailsLiveData: LiveData<Character> = characterDetailsMutableLiveData

    private val navigationMutableLiveData = MutableLiveData<CharacterDetailsNavigationViewState>()
    val navigationLiveData: LiveData<CharacterDetailsNavigationViewState> = navigationMutableLiveData

    private val actionsMutableLiveData = MutableLiveData<CharacterDetailsActionsViewState>()
    val actionsLiveData: LiveData<CharacterDetailsActionsViewState> = actionsMutableLiveData

    private val characterBasics by lazy {
        savedStateHandle.get<CharacterBasics>(
            EXTRA_CHARACTER_BASICS
        )
    }

    fun loadInfo() {
        characterBasics?.let {
            loadCharacterDetails(it)
        }
    }

    private fun loadCharacterDetails(characterBasics: CharacterBasics) {
        viewModelScope.launch {
            getCharacterFullInfoUseCase.invoke(characterBasics) { character ->
                characterDetailsMutableLiveData.postValue(character)
            }
        }
    }

    fun onMenuClicked() {
        navigationMutableLiveData.value = OpenMenuState
    }

    fun onQuoteCopied(quote: String) {
        actionsMutableLiveData.postValue(CopiedQuoteState("Quote", quote))
    }

    sealed class CharacterDetailsNavigationViewState {
        object OpenMenuState : CharacterDetailsNavigationViewState()
    }

    sealed class CharacterDetailsActionsViewState {
        data class CopiedQuoteState(val label: String, val text: String) : CharacterDetailsActionsViewState()
    }
}