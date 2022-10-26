package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface GameOfThronesQuotesApiServices {
    @GET("v1/character/{slug}")
    fun getCharacter(@Path("slug") slug: String): Result<CharacterDetailsResponse>

    @GET("v1/houses")
    fun getAllHouses(): Response<List<HouseResponse>>
}

class GameOfThronesQuotesApi @Inject constructor() : CharacterDetailsApiInterface,
    HousesApiInterface {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.gameofthronesquotes.xyz/")
        .build()

    private val service: GameOfThronesQuotesApiServices =
        retrofit.create(GameOfThronesQuotesApiServices::class.java)

    override fun getCharacterDetails(firstName: String): Result<CharacterDetailsResponse> {
        return service.getCharacter(firstName)
    }

    override fun getAllHouses(): Response<List<HouseResponse>> {
        return service.getAllHouses()
    }

}