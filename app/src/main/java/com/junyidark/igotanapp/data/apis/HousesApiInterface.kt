package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.HouseResponse

interface HousesApiInterface {
    fun getAllHouses() : List<HouseResponse>
}