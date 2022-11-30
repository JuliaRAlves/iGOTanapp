package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import javax.inject.Inject

interface GetAllHousesListUseCaseInterface {
    fun invoke(onSuccess: (List<House>) -> Unit)
}

class GetAllHousesListUseCase @Inject constructor(
    private val housesRepository: HousesRepositoryInterface
) : GetAllHousesListUseCaseInterface {

    override fun invoke(onSuccess: (List<House>) -> Unit) {
        return housesRepository.getAllHouses { response ->
            onSuccess(response)
        }
    }

}