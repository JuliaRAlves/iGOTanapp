package com.junyidark.igotanapp.data.models

import com.google.gson.annotations.SerializedName

class CharacterBasicsResponse(
    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("house")
    val house: String?
)