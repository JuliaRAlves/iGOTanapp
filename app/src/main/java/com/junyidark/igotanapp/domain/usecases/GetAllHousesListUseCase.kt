package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import javax.inject.Inject

interface GetAllHousesListUseCaseInterface {
    fun invoke(): List<House>
}

class GetAllHousesListUseCase @Inject constructor(
    private val housesRepository: HousesRepositoryInterface
) : GetAllHousesListUseCaseInterface {

    override fun invoke(): List<House> {
        return housesRepository.getAllHouses()
    }

}