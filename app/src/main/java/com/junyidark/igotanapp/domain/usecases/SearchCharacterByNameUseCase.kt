package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface SearchCharacterByNameUseCaseInterface {
    fun invoke(query: String): List<CharacterBasics>
}

class SearchCharacterByNameUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : SearchCharacterByNameUseCaseInterface {

    override fun invoke(query: String): List<CharacterBasics> {
        val charactersList = charactersRepository.getAllCharactersBasics()

        return charactersList.searchFor(query)
    }

    private fun List<CharacterBasics>.searchFor(query: String): List<CharacterBasics> {
        return this.filter { it.name.contains(query) }
    }
}