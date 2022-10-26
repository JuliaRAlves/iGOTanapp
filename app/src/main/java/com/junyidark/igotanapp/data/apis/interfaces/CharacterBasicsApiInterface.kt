package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import retrofit2.Call

interface CharacterBasicsApiInterface {
    fun getAllCharactersBasics(): Call<List<CharacterBasicsResponse>>
}