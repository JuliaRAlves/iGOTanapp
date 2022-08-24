package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.House

interface HousesRepositoryInterface {
    fun getAllHouses(): List<House>
}