package com.junyidark.igotanapp.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.junyidark.igotanapp.domain.models.CharacterBasics
import javax.inject.Inject

class Router @Inject constructor() : RouterInterface {

    companion object {
        private const val AUTHOR_GITHUB_URL = "https://github.com/JuliaRAlves"
    }

    override fun goToCharactersList(context: Context, resultList: List<CharacterBasics>) {
        //TODO criar rota
    }

    override fun goToHousesList(context: Context) {
        //TODO criar rota
    }

    override fun goToAuthor(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(AUTHOR_GITHUB_URL))
        context.startActivity(browserIntent)
    }
}