package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface SearchCharacterByNameUseCaseInterface {
    suspend fun invoke(query: String): Result<List<CharacterBasics>>
}

class SearchCharacterByNameUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : SearchCharacterByNameUseCaseInterface {

    override suspend fun invoke(query: String): Result<List<CharacterBasics>> {
        return charactersRepository.getAllCharactersBasics().map { it.searchFor(query) }
    }

    private fun List<CharacterBasics>.searchFor(query: String): List<CharacterBasics> {
        return this.filter {
            it.name.lowercase().contains(query.lowercase())
        }
    }
}