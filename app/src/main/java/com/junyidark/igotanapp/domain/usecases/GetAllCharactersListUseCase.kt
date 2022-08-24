package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface GetAllCharactersListUseCaseInterface {
    fun invoke(): List<CharacterBasics>
}

class GetAllCharactersListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : GetAllCharactersListUseCaseInterface {

    override fun invoke(): List<CharacterBasics> {
        return charactersRepository.getAllCharactersBasics()
    }

}