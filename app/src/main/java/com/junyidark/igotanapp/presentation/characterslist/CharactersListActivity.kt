package com.junyidark.igotanapp.presentation.characterslist

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListNavigationViewState.GoToCharacterDetailsState
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListActivity : ComponentActivity() {

    private val viewModel by viewModels<CharactersListViewModel>()

    @Inject
    lateinit var router: RouterInterface

    companion object {
        const val EXTRA_CHARACTERS_RESULT_LIST = "EXTRA_CHARACTERS_RESULT_LIST"

        fun getIntent(context: Context, charactersResultList: List<CharacterBasics>): Intent {
            return Intent(context, CharactersListActivity::class.java)
                .putExtra(EXTRA_CHARACTERS_RESULT_LIST, charactersResultList as Serializable)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadList()

        setContent {
            CharactersList()
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.navigationLiveData.observe(this) { viewState ->
            when (viewState) {
                is GoToCharacterDetailsState -> goToCharacterDetails(viewState.character)
                else -> {}
            }
        }
    }

    private fun goToCharacterDetails(character: CharacterBasics) {
        router.goToCharacterDetails(context = this, character = character)
    }

    @Composable
    private fun CharactersList(charactersListViewModel: CharactersListViewModel = viewModel()) {
        IGOTanappTheme {
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
                                    .clickable { charactersListViewModel.onSwitchThemeClicked() }
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

                        val charactersList =
                            charactersListViewModel.charactersListLiveData.observeAsState().value ?: emptyList()

                        LazyColumn {
                            itemsIndexed(charactersList) { index, character ->
                                CharacterListItem(
                                    photoUrl = character.photo,
                                    name = character.name,
                                    title = character.title,
                                    onClick = { viewModel.onCharacterClicked(index) })
                            }
                        }
                    }
                }
            }
        }
    }
}