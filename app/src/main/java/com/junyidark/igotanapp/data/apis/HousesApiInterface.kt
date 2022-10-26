package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Response

interface HousesApiInterface {
    fun getAllHouses() : Response<List<HouseResponse>>
}