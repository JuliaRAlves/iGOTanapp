package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails

interface CharactersRepositoryInterface {
    suspend fun getAllCharactersBasics() : Result<List<CharacterBasics>>
    suspend fun getCharacterDetails(firstName: String) : Result<CharacterDetails>
}