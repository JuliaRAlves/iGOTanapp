package com.junyidark.igotanapp.data.models

import com.google.gson.annotations.SerializedName

class HouseResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("members")
    val members: List<MemberResponse>? = emptyList()
)

class MemberResponse(
    @SerializedName("name")
    val name: String
)