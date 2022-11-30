package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.House

interface HousesRepositoryInterface {
    fun getAllHouses(onSuccess: (List<House>) -> Unit)
}