package com.junyidark.igotanapp.domain.repositories

import com.junyidark.igotanapp.domain.models.House

interface HousesRepositoryInterface {
    suspend fun getAllHouses(): Result<List<House>>
}