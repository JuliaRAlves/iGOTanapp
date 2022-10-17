package com.junyidark.igotanapp.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var router: RouterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.homeLiveData.observe(this) { viewState ->
            when (viewState) {
                is HomeViewModel.HomeViewState.GoToAllCharactersState -> goToAllCharacters()
                is HomeViewModel.HomeViewState.GoToAuthorState -> goToAuthor()
                is HomeViewModel.HomeViewState.GoToTheHousesState -> goToTheHouses()
                is HomeViewModel.HomeViewState.LoadedResultState -> goToResultList(viewState.result)
            }
        }
    }

    private fun goToAllCharacters() {
        router.goToCharactersList(context = this, resultList = emptyList())
    }

    private fun goToAuthor() {
        router.goToAuthor(context = this)
    }

    private fun goToTheHouses() {
        router.goToHousesList(context = this)
    }

    private fun goToResultList(searchResult: List<CharacterBasics>) {
        router.goToCharactersList(context = this, resultList = searchResult)
    }
}

@Composable
private fun Home(homeViewModel: HomeViewModel = viewModel()) {
    IGOTanappTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.secondary,
                            MaterialTheme.colors.background
                        )
                    )
                )
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = dimensionResource(id = R.dimen.padding_16dp))
            ) {
                Spacer(Modifier.heightIn(dimensionResource(id = R.dimen.padding_24dp)))
                Title()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SearchBar { homeViewModel.onTextChanged(it) }
                    SearchButton { homeViewModel.onSearchClicked() }
                    CallToPage(page = stringResource(id = R.string.home_all_characters_page)) { homeViewModel.onAllCharactersClicked() }
                    CallToPage(page = stringResource(id = R.string.home_the_houses_page)) { homeViewModel.onTheHousesClicked() }
                }
                Copyright { homeViewModel.onAuthorNameClicked() }
            }
        }
    }
}