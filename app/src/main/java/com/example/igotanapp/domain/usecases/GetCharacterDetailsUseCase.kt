package com.example.igotanapp.domain.usecases

import com.example.igotanapp.domain.models.CharacterDetails
import javax.inject.Inject

interface GetCharacterDetailsUseCaseInterface {
    fun invoke(): CharacterDetails
}

class GetCharacterDetailsUseCase @Inject constructor(
    //private val charactersRepository: CharactersRepositoryInterface
) : GetCharacterDetailsUseCaseInterface {

    override fun invoke(): CharacterDetails {
        TODO("Not yet implemented")
    }

}