package com.junyidark.igotanapp.data.repositories

import com.junyidark.igotanapp.data.apis.interfaces.HousesApiInterface
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.GetHouseCoatOfArmsUseCaseInterface
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val api: HousesApiInterface,
    private val getHouseCoatOfArmsUseCase: GetHouseCoatOfArmsUseCaseInterface
) : HousesRepositoryInterface {
    override suspend fun getAllHouses(): Result<List<House>> {
        val housesCall = api.getAllHouses()
        val body = housesCall.body()

        return if (housesCall.isSuccessful && body != null) {
            Result.success(body.toDomainObject())
        } else if (housesCall.body() == null) {
            Result.failure(Throwable("CharacterBasicsResponse is null"))
        } else {
            Result.failure(Throwable(housesCall.message()))
        }
    }

    private fun List<HouseResponse>.toDomainObject(): List<House> {
        return this.map { house ->
            House(
                coatOfArms = getHouseCoatOfArmsUseCase.invoke(house.name),
                name = house.name,
                members = house.members?.map { it.name } ?: emptyList()
            )
        }
    }
}