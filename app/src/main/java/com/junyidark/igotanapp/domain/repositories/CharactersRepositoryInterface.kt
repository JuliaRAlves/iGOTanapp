package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import retrofit2.Response

interface CharactersRepositoryInterface {
    fun getAllCharactersBasics(onSuccess: (List<CharacterBasics>) -> Unit)
    fun getCharacterDetails(firstName: String) : CharacterDetails?
}