package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface GetCharacterFullInfoUseCaseInterface {
    suspend fun invoke(characterBasics: CharacterBasics): Result<Character>
}

class GetCharacterFullInfoUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : GetCharacterFullInfoUseCaseInterface {

    override suspend fun invoke(characterBasics: CharacterBasics): Result<Character> {
        val characterName = characterBasics.name
        val characterFirstName = characterName.split(" ").first().lowercase()

        return charactersRepository.getCharacterDetails(characterFirstName).map {
            joinCharacterBasicsAndDetails(characterBasics, it)
        }
    }

    private fun joinCharacterBasicsAndDetails(
        characterBasics: CharacterBasics,
        characterDetails: CharacterDetails
    ): Character {
        val characterHouse = characterDetails.house ?: House(
            coatOfArms = -1,
            name = characterBasics.houseName,
            members = emptyList()
        )

        return Character(
            photo = characterBasics.photo,
            name = characterBasics.name,
            title = characterBasics.title,
            house = characterHouse,
            quotes = characterDetails.quotes
        )
    }
}