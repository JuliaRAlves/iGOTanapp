package com.junyidark.igotanapp.presentation.houseslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetAllHousesListUseCase
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.GoToHouseDetailsState
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.OpenMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HousesListViewModel @Inject constructor(
    private val getAllHousesListUseCase: GetAllHousesListUseCase
) : ViewModel() {

    private val housesListMutableLiveData = MutableLiveData<List<House>>()
    val housesListLiveData: LiveData<List<House>> = housesListMutableLiveData

    private val screenMutableLiveData = MutableLiveData<HousesListViewState>()
    val screenLiveData: LiveData<HousesListViewState> = screenMutableLiveData

    private var housesList = emptyList<House>()

    fun loadList() {
        viewModelScope.launch {
            housesList = getAllHousesListUseCase.invoke()
            housesListMutableLiveData.postValue(housesList)
        }
    }

    fun onHouseClicked(position: Int) {
        screenMutableLiveData.value = GoToHouseDetailsState(housesList[position])
    }

    fun onMenuClicked() {
        screenMutableLiveData.value = OpenMenuState
    }

    sealed class HousesListViewState {
        data class GoToHouseDetailsState(val house: House) : HousesListViewState()
        object OpenMenuState : HousesListViewState()
    }
}