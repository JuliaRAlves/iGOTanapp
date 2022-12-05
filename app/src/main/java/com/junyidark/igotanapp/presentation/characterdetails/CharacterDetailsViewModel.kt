package com.junyidark.igotanapp.presentation.characterdetails

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetCharacterFullInfoUseCaseInterface
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsActivity.Companion.EXTRA_CHARACTER_BASICS
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsActionsViewState.CopiedQuoteState
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

    fun onSwitchThemeClicked() {

    }

    fun onQuoteCopied(quote: String) {
        actionsMutableLiveData.postValue(CopiedQuoteState("Quote", quote))
    }

    sealed class CharacterDetailsActionsViewState {
        data class CopiedQuoteState(val label: String, val text: String) : CharacterDetailsActionsViewState()
    }
}