package com.junyidark.igotanapp.presentation.characterslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.presentation.characterslist.CharactersListViewModel.CharactersListViewState.*
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import dagger.hilt.android.AndroidEntryPoint
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
        viewModel.screenLiveData.observe(this) { viewState ->
            when (viewState) {
                is GoToCharacterDetailsState -> goToCharacterDetails(viewState.character)
                is GoToHomeState -> goToHome()
                is OpenMenuState -> openMenu()
            }
        }
    }

    private fun goToHome() {
        router.goToHome(context = this)
    }

    private fun goToCharacterDetails(character: Character) {
        router.goToCharacterDetails(context = this, character = character)
    }

    private fun openMenu() {

    }

}

@Composable
private fun CharactersList(charactersListViewModel: CharactersListViewModel = viewModel()) {
    IGOTanappTheme {

    }
}