package com.junyidark.igotanapp.domain.usecases

import com.junyidark.igotanapp.R
import javax.inject.Inject

interface GetHouseCoatOfArmsUseCaseInterface {
    fun invoke(houseName: String): Int
}

class GetHouseCoatOfArmsUseCase @Inject constructor() : GetHouseCoatOfArmsUseCaseInterface {
    override fun invoke(houseName: String): Int {
        return when (houseName) {
            "House Stark" -> R.drawable.ic_launcher_foreground
            else -> R.drawable.ic_launcher_background
        }
    }
}