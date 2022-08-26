package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface GameOfThronesQuotesApiServices {
    @GET("v1/character/{slug}")
    fun getCharacter(@Path("slug") slug: String): Result<CharacterDetailsResponse>

    @GET("v1/houses")
    fun getAllHouses(): Result<List<HouseResponse>>
}

class GameOfThronesQuotesApi : CharacterDetailsApiInterface, HousesApiInterface {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.gameofthronesquotes.xyz/")
        .build()

    private val service: GameOfThronesQuotesApiServices = retrofit.create(GameOfThronesQuotesApiServices::class.java)

    override fun getCharacterDetails(firstName: String): Result<CharacterDetailsResponse> {
        return service.getCharacter(firstName)
    }

    override fun getAllHouses(): Result<List<HouseResponse>> {
        return service.getAllHouses()
    }

}