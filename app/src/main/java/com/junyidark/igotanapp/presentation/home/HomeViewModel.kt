package com.junyidark.igotanapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCaseInterface
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToAllCharactersState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToAuthorState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.GoToTheHousesState
import com.junyidark.igotanapp.presentation.home.HomeViewModel.HomeViewState.LoadedResultState
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

    private val queryMutableLiveData = MutableLiveData("")
    val queryLiveData: LiveData<String> = queryMutableLiveData

    fun onSearchClicked() {
        val query = queryLiveData.value.takeIf { it?.isNotEmpty() == true } ?: return
        isLoadingMutableLiveData.value = true

        viewModelScope.launch {
            searchCharacterByNameUseCase.invoke(query)
                .onSuccess { list ->
                    screenMutableLiveData.postValue(LoadedResultState(list))
                    isLoadingMutableLiveData.postValue(false)
                }
                .onFailure { isOnErrorMutableLiveData.postValue(true) }
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
        queryMutableLiveData.value = text
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