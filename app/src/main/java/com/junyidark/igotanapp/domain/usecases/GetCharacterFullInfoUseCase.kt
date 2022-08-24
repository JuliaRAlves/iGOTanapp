package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface GetCharacterFullInfoUseCaseInterface {
    fun invoke(characterBasics: CharacterBasics): Character
}

class GetCharacterFullInfoUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : GetCharacterFullInfoUseCaseInterface {

    override fun invoke(characterBasics: CharacterBasics): Character {
        val characterName = characterBasics.name
        val characterFirstName = characterName.split(" ").first().lowercase()
        val characterDetails = charactersRepository.getCharacterDetails(characterFirstName)

        return joinCharacterBasicsAndDetails(characterBasics, characterDetails)
    }

    private fun joinCharacterBasicsAndDetails(
        characterBasics: CharacterBasics,
        characterDetails: CharacterDetails
    ): Character {
        val characterHouse = characterDetails.house?.name ?: characterBasics.houseName

        return Character(
            photo = characterBasics.photo,
            name = characterBasics.name,
            title = characterBasics.title,
            house = House(
                coatOfArms = -1,
                name = characterHouse,
                members = emptyList()
            ),
            quotes = characterDetails.quotes
        )
    }
}