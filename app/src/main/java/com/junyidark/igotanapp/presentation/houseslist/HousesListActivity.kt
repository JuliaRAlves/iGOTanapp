package com.junyidark.igotanapp.presentation.houseslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HousesListActivity : ComponentActivity() {

    private val viewModel by viewModels<HousesListViewModel>()

    @Inject
    lateinit var router: RouterInterface

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HousesListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadList()

        setContent {
            HousesList()
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.screenLiveData.observe(this) { viewState ->
            when (viewState) {
                is HousesListViewModel.HousesListViewState.GoToHomeState -> goToHome()
                is HousesListViewModel.HousesListViewState.GoToHouseDetailsState -> goToHouseDetails(viewState.house)
                is HousesListViewModel.HousesListViewState.OpenMenuState -> openMenu()
            }
        }
    }

    private fun goToHome() {
        router.goToHome(context = this)
    }

    private fun goToHouseDetails(house: House) {
        router.goToHouseDetails(context = this, house = house)
    }

    private fun openMenu() {


    }
}

@Composable
private fun HousesList(housesListViewModel: HousesListViewModel = viewModel()) {
    IGOTanappTheme {

    }
}