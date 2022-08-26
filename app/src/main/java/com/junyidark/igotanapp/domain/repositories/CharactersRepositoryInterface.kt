package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails

interface CharactersRepositoryInterface {
    fun getAllCharactersBasics() : List<CharacterBasics>
    fun getCharacterDetails(firstName: String) : CharacterDetails?
}