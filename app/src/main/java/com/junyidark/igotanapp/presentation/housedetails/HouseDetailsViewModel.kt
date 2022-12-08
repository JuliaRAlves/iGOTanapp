package com.junyidark.igotanapp.presentation.housedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.usecases.GetThemeUseCaseInterface
import com.junyidark.igotanapp.domain.usecases.SetThemeUseCaseInterface
import com.junyidark.igotanapp.presentation.housedetails.HouseDetailsActivity.Companion.EXTRA_HOUSE
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getThemeUseCase: GetThemeUseCaseInterface,
    private val setThemeUseCase: SetThemeUseCaseInterface
) : ViewModel() {

    private val houseDetailsMutableLiveData = MutableLiveData<House>()
    val houseDetailsLiveData: LiveData<House> = houseDetailsMutableLiveData

    private val themeMutableLiveData = MutableLiveData<Theme>()
    val themeLiveData: LiveData<Theme> = themeMutableLiveData

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

}