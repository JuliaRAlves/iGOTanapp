package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import retrofit2.Call

interface CharacterDetailsApiInterface {
    fun getCharacterDetails(firstName: String): Call<CharacterDetailsResponse>
}