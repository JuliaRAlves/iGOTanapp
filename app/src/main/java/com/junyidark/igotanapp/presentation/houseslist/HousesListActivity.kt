package com.junyidark.igotanapp.presentation.houseslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.houseslist.HousesListViewModel.HousesListViewState.GoToHouseDetailsState
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

    override fun onResume() {
        super.onResume()
        viewModel.updateTheme()
    }

    private fun setObservers() {
        viewModel.screenLiveData.observe(this) { viewState ->
            when (viewState) {
                is GoToHouseDetailsState -> goToHouseDetails(viewState.house)
            }
        }
    }

    private fun goToHouseDetails(house: House) {
        router.goToHouseDetails(context = this, house = house)
    }

    @Composable
    private fun HousesList(housesListViewModel: HousesListViewModel = viewModel()) {
        val theme: Theme by viewModel.themeLiveData.observeAsState(initial = viewModel.getTheme())

        IGOTanappTheme(theme) {
            Surface(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxHeight()
            ) {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalDrawer(
                    drawerState = drawerState,
                    drawerBackgroundColor = MaterialTheme.colors.secondary,
                    drawerContentColor = MaterialTheme.colors.onSecondary,
                    drawerElevation = dimensionResource(id = R.dimen.drawer_elevation),
                    drawerContent = {
                        Column {
                            Text(
                                text = stringResource(id = R.string.drawer_change_theme),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(id = R.dimen.padding_16dp))
                                    .clickable { housesListViewModel.onSwitchThemeClicked() }
                            )
                            Text(
                                text = stringResource(id = R.string.drawer_close),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(id = R.dimen.padding_16dp))
                                    .clickable { scope.launch { drawerState.close() } }
                            )
                        }
                    }
                ) {
                    Column {
                        Toolbar(
                            onClickBack = { onBackPressed() },
                            onClickMenu = { scope.launch { drawerState.open() } })

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