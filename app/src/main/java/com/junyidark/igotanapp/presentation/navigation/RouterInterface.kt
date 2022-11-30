package com.junyidark.igotanapp.presentation.navigation

import android.content.Context
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.House

interface RouterInterface {
    fun goToHome(context: Context)
    fun goToCharactersList(context: Context, resultList: List<CharacterBasics>)
    fun goToCharacterDetails(context: Context, character: CharacterBasics)
    fun goToHousesList(context: Context)
    fun goToHouseDetails(context: Context, house: House)
    fun goToAuthor(context: Context)
}