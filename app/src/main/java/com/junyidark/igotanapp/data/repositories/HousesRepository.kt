package com.junyidark.igotanapp.data.repositories

import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.data.apis.HousesApiInterface
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val api: HousesApiInterface
) : HousesRepositoryInterface {
    override fun getAllHouses(): List<House> {
        return api.getAllHouses().toDomainObject()
    }

    private fun List<HouseResponse>.toDomainObject(): List<House> {
        return this.map { house ->
            House(
                coatOfArms = getCoatOfArmsByName(house.name),
                name = house.name,
                members = house.members
            )
        }
    }

    private fun getCoatOfArmsByName(houseName: String): Int {
        return when (houseName) {
            "House Stark" -> R.drawable.ic_launcher_foreground
            else -> R.drawable.ic_launcher_background
        }
    }
}