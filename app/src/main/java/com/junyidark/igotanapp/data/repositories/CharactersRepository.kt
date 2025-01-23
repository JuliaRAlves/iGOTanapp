package com.junyidark.igotanapp.data.repositories

import com.junyidark.igotanapp.data.apis.interfaces.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.models.Quote
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val basicsApi: CharacterBasicsApiInterface,
    private val detailsApi: CharacterDetailsApiInterface
) : CharactersRepositoryInterface {
    override suspend fun getAllCharactersBasics(): Result<List<CharacterBasics>> {
        val allCharactersBasicsCall = basicsApi.getAllCharactersBasics()
        val body = allCharactersBasicsCall.body()

        return if (allCharactersBasicsCall.isSuccessful && body != null) {
            Result.success(body.toDomainObject())
        } else if (allCharactersBasicsCall.body() == null) {
            Result.failure(Throwable("CharacterBasicsResponse is null"))
        } else {
            Result.failure(Throwable(allCharactersBasicsCall.message()))
        }
    }

    override suspend fun getCharacterDetails(firstName: String): Result<CharacterDetails> {
        val characterDetailsCall = detailsApi.getCharacterDetails(firstName)
        val item = characterDetailsCall.body()?.firstOrNull()

        return if (characterDetailsCall.isSuccessful && item != null) {
            Result.success(item.toDomainObject())
        } else if (characterDetailsCall.body() == null) {
            Result.failure(Throwable("CharacterDetailsResponse is null"))
        } else {
            Result.failure(Throwable(characterDetailsCall.message()))
        }
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
            members = house.members?.map { it.name } ?: emptyList()
        )
        val domainQuotes = quotes.map { Quote(text = it) }

        return CharacterDetails(
            house = domainHouse,
            quotes = domainQuotes
        )
    }
}