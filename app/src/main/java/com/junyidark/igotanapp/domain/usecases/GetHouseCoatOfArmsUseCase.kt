package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.R
import javax.inject.Inject

interface GetHouseCoatOfArmsUseCaseInterface {
    fun invoke(houseName: String): Int
}

class GetHouseCoatOfArmsUseCase @Inject constructor() : GetHouseCoatOfArmsUseCaseInterface {
    override fun invoke(houseName: String): Int {
        return when {
            houseName.contains(" Stark ") -> R.drawable.house_stark
            houseName.contains(" Lannister ") -> R.drawable.house_lannister
            houseName.contains(" Baratheon ") -> R.drawable.house_baratheon
            houseName.contains(" Targaryen ") -> R.drawable.house_targaryen
            houseName.contains(" Greyjoy ") -> R.drawable.house_greyjoy
            houseName.contains(" Tarly ") -> R.drawable.house_tarly
            houseName.contains(" Tarth ") -> R.drawable.house_tarth
            houseName.contains(" Bolton ") -> R.drawable.house_bolton
            houseName.contains(" Baelish ") -> R.drawable.house_baelish
            houseName.contains(" Tyrell ") -> R.drawable.house_tyrell
            else -> R.drawable.house_generic
        }
    }
}