package com.junyidark.igotanapp.presentation.houseslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetAllHousesListUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.GoToHouseDetailsState
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HousesListViewModel @Inject constructor(
    private val getAllHousesListUseCase: GetAllHousesListUseCaseInterface,
    private val getThemeUseCase: GetThemeUseCaseInterface,
    private val setThemeUseCase: SetThemeUseCaseInterface
) : ViewModel() {

    private val housesListMutableLiveData = MutableLiveData<List<House>>()
    val housesListLiveData: LiveData<List<House>> = housesListMutableLiveData

    private val screenMutableLiveData = MutableLiveData<HousesListViewState>()
    val screenLiveData: LiveData<HousesListViewState> = screenMutableLiveData

    private val themeMutableLiveData = MutableLiveData<Theme>()
    val themeLiveData: LiveData<Theme> = themeMutableLiveData

    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = isLoadingMutableLiveData

    private val isOnErrorMutableLiveData = MutableLiveData(false)
    val isOnErrorLiveData: LiveData<Boolean> = isOnErrorMutableLiveData

    fun loadList() {
        isLoadingMutableLiveData.value = true
        viewModelScope.launch {
            getAllHousesListUseCase.invoke()
                .onSuccess { list ->
                    housesListMutableLiveData.postValue(list)
                    isLoadingMutableLiveData.postValue(false)
                }
                .onFailure { isOnErrorMutableLiveData.postValue(true) }
        }
    }

    fun onHouseClicked(position: Int) {
        val list = housesListLiveData.value.orEmpty()
        val house = list.getOrNull(position)
        house?.let {
            screenMutableLiveData.value = GoToHouseDetailsState(it)
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

    sealed class HousesListViewState {
        data class GoToHouseDetailsState(val house: House) : HousesListViewState()
    }
}