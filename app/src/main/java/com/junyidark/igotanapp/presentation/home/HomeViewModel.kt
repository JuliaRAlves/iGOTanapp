package com.junyidark.igotanapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCaseInterface
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.*
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchCharacterByNameUseCase: SearchCharacterByNameUseCaseInterface,
    private val getThemeUseCase: GetThemeUseCaseInterface
) : ViewModel() {

    private val screenMutableLiveData = MutableLiveData<HomeViewState>()
    val screenLiveData: LiveData<HomeViewState> = screenMutableLiveData

    private val themeMutableLiveData = MutableLiveData<Theme>()
    val themeLiveData: LiveData<Theme> = themeMutableLiveData

    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = isLoadingMutableLiveData

    private val isOnErrorMutableLiveData = MutableLiveData(false)
    val isOnErrorLiveData: LiveData<Boolean> = isOnErrorMutableLiveData

    private var query: String = ""

    fun onSearchClicked() {
        isLoadingMutableLiveData.value = true

        viewModelScope.launch {
            if (query.isNotEmpty()) {
                searchCharacterByNameUseCase.invoke(query, onSuccess = { list ->
                    screenMutableLiveData.postValue(LoadedResultState(list))
                    isLoadingMutableLiveData.postValue(false)
                },
                    onError = { isOnErrorMutableLiveData.postValue(true) }
                )
            } else {
                isLoadingMutableLiveData.postValue(false)
            }
        }
    }

    fun onAllCharactersClicked() {
        screenMutableLiveData.value = GoToAllCharactersState
    }

    fun onTheHousesClicked() {
        screenMutableLiveData.value = GoToTheHousesState
    }

    fun onAuthorNameClicked() {
        screenMutableLiveData.value = GoToAuthorState
    }

    fun onTextChanged(text: String) {
        query = text
    }

    fun updateTheme() {
        themeMutableLiveData.value = getThemeUseCase.invoke()
    }

    sealed class HomeViewState {
        data class LoadedResultState(val result: List<CharacterBasics>) : HomeViewState()
        object GoToAllCharactersState : HomeViewState()
        object GoToTheHousesState : HomeViewState()
        object GoToAuthorState : HomeViewState()
    }
}