package com.example.igotanapp.domain.usecases

import com.example.igotanapp.domain.models.CharacterBasics
import javax.inject.Inject

interface GetAllCharactersListUseCaseInterface {
    fun invoke(): List<CharacterBasics>
}

class GetAllCharactersListUseCase @Inject constructor(
    //private val charactersRepository: CharactersRepositoryInterface
) : GetAllCharactersListUseCaseInterface {

    override fun invoke(): List<CharacterBasics> {
        TODO("Not yet implemented")
    }

}