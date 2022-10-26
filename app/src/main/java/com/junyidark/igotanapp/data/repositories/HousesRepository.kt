package com.junyidark.igotanapp.data.repositories

import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.data.apis.HousesApiInterface
import com.junyidark.igotanapp.data.apis.MockApi
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.GetHouseCoatOfArmsUseCaseInterface
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val api: HousesApiInterface,
    private val getHouseCoatOfArmsUseCase: GetHouseCoatOfArmsUseCaseInterface
) : HousesRepositoryInterface {
    override fun getAllHouses(): List<House> {
        // TODO: use api
        val result = MockApi().getAllHouses().body()

        return result?.toDomainObject() ?: emptyList()
    }

    private fun List<HouseResponse>.toDomainObject(): List<House> {
        return this.map { house ->
            House(
                coatOfArms = getHouseCoatOfArmsUseCase.invoke(house.name),
                name = house.name,
                members = house.members
            )
        }
    }
}