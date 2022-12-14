package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.apis.interfaces.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.HousesApiInterface
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface GameOfThronesQuotesApiServices {
    @GET("v1/character/{slug}")
    fun getCharacter(@Path("slug") slug: String): Call<List<CharacterDetailsResponse>>

    @GET("v1/houses")
    fun getAllHouses(): Call<List<HouseResponse>>
}

class GameOfThronesQuotesApi @Inject constructor(
    private val services: GameOfThronesQuotesApiServices
) : CharacterDetailsApiInterface,
    HousesApiInterface {

    override fun getCharacterDetails(firstName: String): Call<List<CharacterDetailsResponse>> {
        return services.getCharacter(firstName)
    }

    override fun getAllHouses(): Call<List<HouseResponse>> {
        return services.getAllHouses()
    }

}