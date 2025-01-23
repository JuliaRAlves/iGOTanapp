package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Response

interface HousesApiInterface {
    suspend fun getAllHouses(): Response<List<HouseResponse>>
}