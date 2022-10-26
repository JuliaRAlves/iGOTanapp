package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import retrofit2.Response
import javax.inject.Inject

interface GetAllCharactersListUseCaseInterface {
    fun invoke(onSuccess: (List<CharacterBasics>) -> Unit)
}

class GetAllCharactersListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepositoryInterface
) : GetAllCharactersListUseCaseInterface {

    override fun invoke(onSuccess: (List<CharacterBasics>) -> Unit){
        charactersRepository.getAllCharactersBasics() { response ->
            onSuccess(response)
        }
    }

}