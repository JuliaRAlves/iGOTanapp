package com.junyidark.igotanapp.data.apis.interfaces

import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Call

interface HousesApiInterface {
    fun getAllHouses(): Call<List<HouseResponse>>
}