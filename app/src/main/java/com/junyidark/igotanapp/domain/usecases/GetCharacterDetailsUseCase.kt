package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface GetCharacterDetailsUseCaseInterface {
    fun invoke(characterName: String): CharacterDetails
}

class GetCharacterDetailsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : GetCharacterDetailsUseCaseInterface {

    override fun invoke(characterName: String): CharacterDetails {
        val characterFirstName = characterName.split(" ").first().lowercase()

        return charactersRepository.getCharacterDetails(characterFirstName)
    }

}