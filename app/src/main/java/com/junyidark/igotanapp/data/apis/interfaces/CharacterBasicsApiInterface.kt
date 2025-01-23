package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import retrofit2.Response

interface CharacterBasicsApiInterface {
   suspend fun getAllCharactersBasics(): Response<List<CharacterBasicsResponse>>
}