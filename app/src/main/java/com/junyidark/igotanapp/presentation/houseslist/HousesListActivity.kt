package com.junyidark.igotanapp.presentation.houseslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.GoToHouseDetailsState
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.OpenMenuState
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
                is GoToHouseDetailsState -> goToHouseDetails(viewState.house)
                is OpenMenuState -> openMenu()
            }
        }
    }

    private fun goToHouseDetails(house: House) {
        router.goToHouseDetails(context = this, house = house)
    }

    private fun openMenu() {

    }

    @Composable
    private fun HousesList(housesListViewModel: HousesListViewModel = viewModel()) {
        IGOTanappTheme {
            IGOTanappTheme {
                Surface(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column {
                        Toolbar(onClickBack = { onBackPressed() }, onClickMenu = { viewModel.onMenuClicked() })

                        val housesList =
                            housesListViewModel.housesListLiveData.observeAsState().value ?: emptyList()

                        LazyColumn {
                            itemsIndexed(housesList) { index, house ->
                                HouseListItem(
                                    coatOfArms = house.coatOfArms,
                                    name = house.name,
                                    onClick = { viewModel.onHouseClicked(index) })
                            }
                        }
                    }
                }
            }
        }
    }
}