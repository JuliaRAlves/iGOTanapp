package com.junyidark.igotanapp.domain.models

data class Character(
    val photo: String,
    val name: String,
    val title: String,
    val house: House,
    val quotes: List<Quote>
)