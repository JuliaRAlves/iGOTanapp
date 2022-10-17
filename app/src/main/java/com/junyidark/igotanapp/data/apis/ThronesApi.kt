package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

interface ThronesApiServices {
    @GET("api/v2/Characters")
    fun getAllCharacters(): Result<List<CharacterBasicsResponse>>
}

class ThronesApi @Inject constructor() : CharacterBasicsApiInterface {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://thronesapi.com/")
        .build()

    private val service: ThronesApiServices = retrofit.create(ThronesApiServices::class.java)

    override fun getAllCharactersBasics(): Result<List<CharacterBasicsResponse>> {
        return service.getAllCharacters()
    }

}