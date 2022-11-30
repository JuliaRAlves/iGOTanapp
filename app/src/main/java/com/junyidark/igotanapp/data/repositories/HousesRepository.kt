package com.junyidark.igotanapp.data.repositories

import android.util.Log
import com.junyidark.igotanapp.data.apis.interfaces.HousesApiInterface
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.GetHouseCoatOfArmsUseCaseInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val api: HousesApiInterface,
    private val getHouseCoatOfArmsUseCase: GetHouseCoatOfArmsUseCaseInterface
) : HousesRepositoryInterface {
    override fun getAllHouses(onSuccess: (List<House>) -> Unit) {
        api.getAllHouses().enqueue(object : Callback<List<HouseResponse>> {
            override fun onResponse(
                call: Call<List<HouseResponse>>,
                response: Response<List<HouseResponse>>
            ) {
                onSuccess(response.body()?.toDomainObject() ?: emptyList())
            }

            override fun onFailure(call: Call<List<HouseResponse>>, t: Throwable) {
                Log.e("Houses api", "onFailure: getAllHouses ")
            }

        })
    }

    private fun List<HouseResponse>.toDomainObject(): List<House> {
        return this.map { house ->
            House(
                coatOfArms = getHouseCoatOfArmsUseCase.invoke(house.name),
                name = house.name,
                members = house.members.map { it.name }
            )
        }
    }
}