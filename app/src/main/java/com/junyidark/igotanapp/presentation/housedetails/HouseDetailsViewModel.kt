package com.junyidark.igotanapp.presentation.housedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.housedetails.HouseDetailsActivity.Companion.EXTRA_HOUSE
import com.junyidark.igotanapp.presentation.housedetails.HouseDetailsViewModel.HouseDetailsNavigationViewState.OpenMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val houseDetailsMutableLiveData = MutableLiveData<House>()
    val houseDetailsLiveData: LiveData<House> = houseDetailsMutableLiveData

    private val navigationMutableLiveData = MutableLiveData<HouseDetailsNavigationViewState>()
    val navigationLiveData: LiveData<HouseDetailsNavigationViewState> = navigationMutableLiveData

    private val house by lazy {
        savedStateHandle.get<House>(
            EXTRA_HOUSE
        )
    }

    fun loadInfo() {
        house?.let {
            houseDetailsMutableLiveData.value = it
        }
    }

    fun onMenuClicked() {
        navigationMutableLiveData.value = OpenMenuState
    }

    sealed class HouseDetailsNavigationViewState {
        object OpenMenuState : HouseDetailsNavigationViewState()
    }

}