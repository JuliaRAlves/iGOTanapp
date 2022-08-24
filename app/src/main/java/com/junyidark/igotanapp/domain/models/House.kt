package com.junyidark.igotanapp.domain.models;

import androidx.annotation.DrawableRes

data class House(
    @DrawableRes val coatOfArms: Int,
    val name: String,
    val members: List<String>
)