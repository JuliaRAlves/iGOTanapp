package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import javax.inject.Inject

interface SearchCharacterByNameUseCaseInterface {
    fun invoke(query: String, onSuccess: (List<CharacterBasics>) -> Unit, onError: () -> Unit)
}

class SearchCharacterByNameUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : SearchCharacterByNameUseCaseInterface {

    override fun invoke(query: String, onSuccess: (List<CharacterBasics>) -> Unit, onError: () -> Unit) {
        charactersRepository.getAllCharactersBasics(
            onSuccess = { charactersList ->
                val resultList = charactersList.searchFor(query)
                onSuccess(resultList)
            },
            onError = { onError() }
        )
    }

    private fun List<CharacterBasics>.searchFor(query: String): List<CharacterBasics> {
        return this.filter {
            it.name.lowercase().contains(query.lowercase())
        }
    }
}