package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.apis.interfaces.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import retrofit2.Call
import retrofit2.http.GET
import javax.inject.Inject

interface ThronesApiServices {
    @GET("api/v2/Characters")
    fun getAllCharacters(): Call<List<CharacterBasicsResponse>>
}

class ThronesApi @Inject constructor(
    private val services: ThronesApiServices
) : CharacterBasicsApiInterface {

    override fun getAllCharactersBasics(): Call<List<CharacterBasicsResponse>> {
        return services.getAllCharacters()
    }

}