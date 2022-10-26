package com.junyidark.igotanapp.data.repositories

import android.util.Log
import com.junyidark.igotanapp.data.apis.interfaces.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.models.Quote
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val basicsApi: CharacterBasicsApiInterface,
    private val detailsApi: CharacterDetailsApiInterface
) : CharactersRepositoryInterface {
    override fun getAllCharactersBasics(
        onSuccess: (List<CharacterBasics>) -> Unit
    ){
        // TODO: use basicsApi
        val result = basicsApi.getAllCharactersBasics().enqueue(object : Callback<List<CharacterBasicsResponse>>{
            override fun onResponse(
                call: Call<List<CharacterBasicsResponse>>,
                response: Response<List<CharacterBasicsResponse>>
            ) {
                onSuccess(response.body()?.toDomainObject() ?: emptyList())
            }

            override fun onFailure(call: Call<List<CharacterBasicsResponse>>, t: Throwable) {
                Log.e("Characters basics api", "onFailure: getAllCharactersBasics ")
            }

        })
    }

    override fun getCharacterDetails(firstName: String): CharacterDetails? {
        val result = detailsApi.getCharacterDetails(firstName).enqueue(object : Callback<CharacterDetailsResponse> {
            override fun onResponse(
                call: Call<CharacterDetailsResponse>,
                response: Response<CharacterDetailsResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<CharacterDetailsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return CharacterDetails(house = null, quotes = emptyList())
    }

    private fun List<CharacterBasicsResponse>.toDomainObject(): List<CharacterBasics> {
        return this.map { character ->
            CharacterBasics(
                photo = character.imageUrl ?: "",
                name = character.fullName ?: "",
                title = character.title ?: "",
                houseName = character.house ?: ""
            )
        }
    }

    private fun CharacterDetailsResponse.toDomainObject(): CharacterDetails {
        val domainHouse = if (house == null) null else House(
            coatOfArms = -1,
            name = house.name,
            members = house.members
        )
        val domainQuotes = quotes.map { Quote(text = it) }

        return CharacterDetails(
            house = domainHouse,
            quotes = domainQuotes
        )
    }
}