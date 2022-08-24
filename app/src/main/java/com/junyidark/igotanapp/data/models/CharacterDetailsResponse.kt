package com.junyidark.igotanapp.data.models

import com.google.gson.annotations.SerializedName

class CharacterDetailsResponse(
    @SerializedName("house")
    val house: HouseResponse?,

    @SerializedName("quotes")
    val quotes: List<String> = emptyList()
)