package com.junyidark.igotanapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCase
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchCharacterByNameUseCase: SearchCharacterByNameUseCase
) : ViewModel() {

    private val homeMutableLiveData = MutableLiveData<HomeViewState>()
    val homeLiveData: LiveData<HomeViewState> = homeMutableLiveData

    private var query: String = ""

    fun onSearchClicked() {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val list = searchCharacterByNameUseCase.invoke(query)
                homeMutableLiveData.postValue(LoadedResultState(list))
            }
        }
    }

    fun onAllCharactersClicked() {
        homeMutableLiveData.value = GoToAllCharactersState
    }

    fun onTheHousesClicked() {
        homeMutableLiveData.value = GoToTheHousesState
    }

    fun onAuthorNameClicked() {
        homeMutableLiveData.value = GoToAuthorState
    }

    fun onTextChanged(text: String) {
        query = text
    }

    sealed class HomeViewState {
        data class LoadedResultState(val result: List<CharacterBasics>) : HomeViewState()
        object GoToAllCharactersState : HomeViewState()
        object GoToTheHousesState : HomeViewState()
        object GoToAuthorState : HomeViewState()
    }
}