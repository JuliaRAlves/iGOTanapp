package com.junyidark.igotanapp.presentation.characterdetails

import androidx.lifecycle.*
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetCharacterFullInfoUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsActivity.Companion.EXTRA_CHARACTER_BASICS
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsActionsViewState.CopiedQuoteState
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterFullInfoUseCase: GetCharacterFullInfoUseCaseInterface,
    private val getThemeUseCase: GetThemeUseCaseInterface,
    private val setThemeUseCase: SetThemeUseCaseInterface
) : ViewModel() {

    private val characterDetailsMutableLiveData = MutableLiveData<Character>()
    val characterDetailsLiveData: LiveData<Character> = characterDetailsMutableLiveData

    private val actionsMutableLiveData = MutableLiveData<CharacterDetailsActionsViewState>()
    val actionsLiveData: LiveData<CharacterDetailsActionsViewState> = actionsMutableLiveData

    private val themeMutableLiveData = MutableLiveData<Theme>()
    val themeLiveData: LiveData<Theme> = themeMutableLiveData

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
        setThemeUseCase.invoke()
        themeMutableLiveData.value = getThemeUseCase.invoke()
    }

    fun getTheme(): Theme {
        return getThemeUseCase.invoke()
    }

    fun updateTheme() {
        themeMutableLiveData.value = getThemeUseCase.invoke()
    }

    fun onQuoteCopied(quote: String) {
        actionsMutableLiveData.postValue(CopiedQuoteState("Quote", quote))
    }

    sealed class CharacterDetailsActionsViewState {
        data class CopiedQuoteState(val label: String, val text: String) : CharacterDetailsActionsViewState()
    }
}