package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import retrofit2.Response

interface CharacterBasicsApiInterface {
    fun getAllCharactersBasics(): Response<List<CharacterBasicsResponse>>
}