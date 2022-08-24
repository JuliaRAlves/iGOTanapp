package com.junyidark.igotanapp.data.repositories

import com.junyidark.igotanapp.data.apis.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.CharacterDetailsApiInterface
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
    override fun getAllCharactersBasics(): List<CharacterBasics> {
        return basicsApi.getAllCharactersBasics().toDomainObject()
    }

    override fun getCharacterDetails(firstName: String): CharacterDetails {
        return detailsApi.getCharacterDetails().toDomainObject()
    }

    private fun List<CharacterBasicsResponse>.toDomainObject(): List<CharacterBasics> {
        return this.map { character ->
            CharacterBasics(
                photo = character.imageUrl,
                name = character.fullName,
                title = character.title,
                houseName = character.house
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