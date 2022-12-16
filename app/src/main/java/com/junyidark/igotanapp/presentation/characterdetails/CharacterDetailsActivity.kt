package com.junyidark.igotanapp.presentation.characterdetails

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsViewModel.CharacterDetailsActionsViewState.CopiedQuoteState
import com.junyidark.igotanapp.presentation.core.*
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsActivity : ComponentActivity() {

    private val viewModel by viewModels<CharacterDetailsViewModel>()

    @Inject
    lateinit var clipboardManager: ClipboardManager

    companion object {
        const val EXTRA_CHARACTER_BASICS = "EXTRA_CHARACTER_BASICS"

        fun getIntent(context: Context, characterBasics: CharacterBasics): Intent {
            return Intent(context, CharacterDetailsActivity::class.java)
                .putExtra(EXTRA_CHARACTER_BASICS, characterBasics)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadInfo()

        setContent {
            CharacterDetails()
        }

        setObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateTheme()
    }

    private fun setObservers() {
        viewModel.actionsLiveData.observe(this) { viewState ->
            when (viewState) {
                is CopiedQuoteState -> sendQuoteToClipboard(viewState.label, viewState.text)
                else -> {}
            }
        }
    }

    private fun sendQuoteToClipboard(label: String, text: String) {
        val clip = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clip)

        Toast.makeText(this, getString(R.string.character_details_toast_message), Toast.LENGTH_LONG).show()
    }

    @Composable
    private fun CharacterDetails(characterDetailsViewModel: CharacterDetailsViewModel = viewModel()) {
        val theme: Theme by viewModel.themeLiveData.observeAsState(initial = viewModel.getTheme())

        IGOTanappTheme(theme) {
            Surface(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxHeight()
            ) {
                val defaultPadding = dimensionResource(id = R.dimen.padding_16dp)

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val isLoading = characterDetailsViewModel.isLoadingLiveData.observeAsState().value ?: false
                val isError = characterDetailsViewModel.isOnErrorLiveData.observeAsState().value ?: false

                when {
                    isLoading -> LoadingScreen()
                    isError -> ErrorScreen()
                    else -> {
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
                                            .clickable { characterDetailsViewModel.onSwitchThemeClicked() }
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

                                val character =
                                    characterDetailsViewModel.characterDetailsLiveData.observeAsState().value

                                character?.let { character ->
                                    PhotoAndName(
                                        photoUrl = character.photo,
                                        name = character.name,
                                        modifier = Modifier.padding(
                                            top = dimensionResource(id = R.dimen.padding_64dp),
                                            start = defaultPadding,
                                            end = defaultPadding
                                        )
                                    )

                                    LabelContent(
                                        label = stringResource(id = R.string.character_details_title_label),
                                        content = character.title,
                                        modifier = Modifier.padding(
                                            top = dimensionResource(id = R.dimen.padding_24dp),
                                            start = defaultPadding,
                                            end = defaultPadding
                                        )
                                    )
                                    LabelContent(
                                        label = stringResource(id = R.string.character_details_family_label),
                                        content = character.house.name,
                                        modifier = Modifier.padding(
                                            top = dimensionResource(id = R.dimen.padding_4dp),
                                            start = defaultPadding,
                                            end = defaultPadding
                                        )
                                    )

                                    Section(
                                        title = stringResource(id = R.string.character_details_quotes_section),
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
                                        items(character.quotes) { quote ->
                                            QuoteCopy(
                                                text = quote.text,
                                                onCopyClicked = {
                                                    viewModel.onQuoteCopied(it)
                                                },
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
        }
    }
}