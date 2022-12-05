package com.junyidark.igotanapp.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.presentation.characterdetails.CharacterDetailsActivity
import com.junyidark.igotanapp.presentation.characterslist.CharactersListActivity
import com.junyidark.igotanapp.presentation.home.HomeActivity
import com.junyidark.igotanapp.presentation.housedetails.HouseDetailsActivity
import com.junyidark.igotanapp.presentation.houseslist.HousesListActivity
import javax.inject.Inject

class Router @Inject constructor() : RouterInterface {

    override fun goToHome(context: Context) {
        val pageIntent = HomeActivity.getIntent(context)
        context.startActivity(pageIntent)
    }

    override fun goToCharactersList(context: Context, resultList: List<CharacterBasics>) {
        val pageIntent = CharactersListActivity.getIntent(context, resultList)
        context.startActivity(pageIntent)
    }

    override fun goToCharacterDetails(context: Context, character: CharacterBasics) {
        val pageIntent = CharacterDetailsActivity.getIntent(context, character)
        context.startActivity(pageIntent)
    }

    override fun goToHousesList(context: Context) {
        val pageIntent = HousesListActivity.getIntent(context)
        context.startActivity(pageIntent)
    }

    override fun goToHouseDetails(context: Context, house: House) {
        val pageIntent = HouseDetailsActivity.getIntent(context, house)
        context.startActivity(pageIntent)
    }

    override fun goToAuthor(context: Context) {
        val link = context.getString(R.string.author_github_url)
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(browserIntent)
    }
}