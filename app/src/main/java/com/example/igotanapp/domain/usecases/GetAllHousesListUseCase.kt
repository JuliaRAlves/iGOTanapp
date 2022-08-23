package com.example.igotanapp.domain.usecases

import com.example.igotanapp.domain.models.House
import javax.inject.Inject

interface GetAllHousesListUseCaseInterface {
    fun invoke(): List<House>
}

class GetAllHousesListUseCase @Inject constructor(
    //private val housesRepository: HousesRepositoryInterface
) : GetAllHousesListUseCaseInterface {

    override fun invoke(): List<House> {
        TODO("Not yet implemented")
    }

}