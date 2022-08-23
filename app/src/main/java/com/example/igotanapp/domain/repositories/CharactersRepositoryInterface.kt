package com.example.igotanapp.domain.repositories

import com.example.igotanapp.domain.models.CharacterBasics
import com.example.igotanapp.domain.models.CharacterDetails

interface CharactersRepositoryInterface {
    fun getAllCharactersBasics() : List<CharacterBasics>
    fun getCharacterDetails() : CharacterDetails
    fun searchCharacterByName(query: String) : List<CharacterBasics>
}