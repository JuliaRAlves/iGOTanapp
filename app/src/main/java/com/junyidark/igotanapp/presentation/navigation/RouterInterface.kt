package com.junyidark.igotanapp.presentation.navigation

import android.content.Context
import com.junyidark.igotanapp.domain.models.CharacterBasics

interface RouterInterface {
    fun goToCharactersList(context: Context, resultList: List<CharacterBasics>)
    fun goToHousesList(context: Context)
    fun goToAuthor(context: Context)
}