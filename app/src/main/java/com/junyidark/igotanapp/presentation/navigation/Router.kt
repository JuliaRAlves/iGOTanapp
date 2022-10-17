package com.junyidark.igotanapp.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.characterslist.CharactersListActivity
import com.junyidark.igotanapp.presentation.home.HomeActivity
import com.junyidark.igotanapp.presentation.houseslist.HousesListActivity
import javax.inject.Inject

class Router @Inject constructor() : RouterInterface {

    companion object {
        private const val AUTHOR_GITHUB_URL = "https://github.com/JuliaRAlves"
    }

    override fun goToHome(context: Context) {
        val pageIntent = HomeActivity.getIntent(context)
        context.startActivity(pageIntent)
    }

    override fun goToCharactersList(context: Context, resultList: List<CharacterBasics>) {
        val pageIntent = CharactersListActivity.getIntent(context, resultList)
        context.startActivity(pageIntent)
    }

    override fun goToCharacterDetails(context: Context, character: Character) {
        //TODO criar rota
    }

    override fun goToHousesList(context: Context) {
        val pageIntent = HousesListActivity.getIntent(context)
        context.startActivity(pageIntent)
    }

    override fun goToHouseDetails(context: Context, house: House) {
        //TODO criar rota
    }

    override fun goToAuthor(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(AUTHOR_GITHUB_URL))
        context.startActivity(browserIntent)
    }
}