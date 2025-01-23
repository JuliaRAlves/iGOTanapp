package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import retrofit2.Call
import retrofit2.Response

interface CharacterDetailsApiInterface {
    suspend fun getCharacterDetails(firstName: String): Response<List<CharacterDetailsResponse>>
}