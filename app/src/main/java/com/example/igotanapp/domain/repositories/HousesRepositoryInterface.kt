package com.example.igotanapp.domain.repositories

import com.example.igotanapp.domain.models.House

interface HousesRepositoryInterface {
    fun getAllHouses(): List<House>
}