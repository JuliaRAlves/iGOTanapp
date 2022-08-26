package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse

interface CharacterBasicsApiInterface {
    fun getAllCharactersBasics(): Result<List<CharacterBasicsResponse>>
}