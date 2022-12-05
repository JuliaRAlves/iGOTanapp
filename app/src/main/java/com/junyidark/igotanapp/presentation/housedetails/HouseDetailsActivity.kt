package com.junyidark.igotanapp.presentation.housedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.core.PhotoAndName
import com.junyidark.igotanapp.presentation.core.Section
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.housedetails.HouseDetailsViewModel.HouseDetailsNavigationViewState.OpenMenuState
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseDetailsActivity : ComponentActivity() {

    private val viewModel by viewModels<HouseDetailsViewModel>()

    companion object {
        const val EXTRA_HOUSE = "EXTRA_HOUSE"

        fun getIntent(context: Context, houseDetails: House): Intent {
            return Intent(context, HouseDetailsActivity::class.java)
                .putExtra(EXTRA_HOUSE, houseDetails)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadInfo()

        setContent {
            HouseDetails()
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.navigationLiveData.observe(this) { viewState ->
            when (viewState) {
                is OpenMenuState -> openMenu()
                else -> {}
            }
        }
    }

    private fun openMenu() {

    }

    @Composable
    private fun HouseDetails(houseDetailsViewModel: HouseDetailsViewModel = viewModel()) {
        IGOTanappTheme {
            Surface(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxHeight()
            ) {
                val defaultPadding = dimensionResource(id = R.dimen.padding_16dp)

                Column {
                    Toolbar(onClickBack = { onBackPressed() }, onClickMenu = { viewModel.onMenuClicked() })

                    val house = houseDetailsViewModel.houseDetailsLiveData.observeAsState().value

                    house?.let { house ->
                        PhotoAndName(
                            drawableRes = house.coatOfArms,
                            name = house.name,
                            modifier = Modifier.padding(
                                top = dimensionResource(id = R.dimen.padding_64dp),
                                start = defaultPadding,
                                end = defaultPadding
                            )
                        )

                        Section(
                            title = stringResource(id = R.string.house_details_members_section),
                            modifier = Modifier.padding(
                                top = defaultPadding,
                                start = defaultPadding,
                                end = defaultPadding
                            )
                        )

                        LazyColumn(
                            modifier = Modifier.padding(
                                top = defaultPadding,
                                start = defaultPadding,
                                end = defaultPadding
                            )
                        ) {
                            items(house.members) { member ->
                                Text(
                                    text = member,
                                    modifier = Modifier.padding(bottom = defaultPadding)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}