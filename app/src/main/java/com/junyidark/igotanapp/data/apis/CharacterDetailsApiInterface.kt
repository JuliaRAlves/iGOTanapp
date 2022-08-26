package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterDetailsResponse

interface CharacterDetailsApiInterface {
    fun getCharacterDetails(firstName: String) : Result<CharacterDetailsResponse>
}