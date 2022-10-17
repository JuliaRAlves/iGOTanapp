package com.junyidark.igotanapp.domain.models

import java.io.Serializable

data class CharacterBasics(
    val photo: String,
    val name: String,
    val title: String,
    val houseName: String
) : Serializable