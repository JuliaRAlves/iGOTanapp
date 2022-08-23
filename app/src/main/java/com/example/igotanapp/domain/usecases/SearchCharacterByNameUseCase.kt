package com.example.igotanapp.domain.usecases

import com.example.igotanapp.domain.models.CharacterBasics
import javax.inject.Inject

interface SearchCharacterByNameUseCaseInterface {
    fun invoke(query: String): List<CharacterBasics>
}

class SearchCharacterByNameUseCase @Inject constructor(
    //private val charactersRepository: CharactersRepositoryInterface
) : SearchCharacterByNameUseCaseInterface {

    override fun invoke(query: String): List<CharacterBasics> {
        TODO("Not yet implemented")
    }

}