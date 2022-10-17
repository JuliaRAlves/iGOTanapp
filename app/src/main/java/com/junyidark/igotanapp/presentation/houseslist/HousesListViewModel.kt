package com.junyidark.igotanapp.presentation.houseslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetAllHousesListUseCase
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HousesListViewModel @Inject constructor(
    private val getAllHousesListUseCase: GetAllHousesListUseCase
) : ViewModel() {

    private val screenMutableLiveData = MutableLiveData<HousesListViewState>()
    val screenLiveData: LiveData<HousesListViewState> = screenMutableLiveData

    private var housesList = emptyList<House>()

    fun loadList() {
        viewModelScope.launch {
            housesList = getAllHousesListUseCase.invoke()
            screenMutableLiveData.postValue(LoadedListState(housesList))
        }
    }

    fun onHouseClicked(position: Int) {
        screenMutableLiveData.value = GoToHouseDetailsState(housesList[position])
    }

    fun onMenuClicked() {
        screenMutableLiveData.value = OpenMenuState
    }

    fun onBackPressed() {
        screenMutableLiveData.value = GoToHomeState
    }

    sealed class HousesListViewState {
        data class LoadedListState(val list: List<House>) : HousesListViewState()
        data class GoToHouseDetailsState(val house: House) : HousesListViewState()
        object GoToHomeState : HousesListViewState()
        object OpenMenuState : HousesListViewState()
    }
}