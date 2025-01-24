package com.junyidark.igotanapp.domain.usecases

import android.content.SharedPreferences
import com.junyidark.igotanapp.presentation.theme.THEME_PREFERENCE
import com.junyidark.igotanapp.presentation.theme.Theme
import javax.inject.Inject

interface GetThemeUseCaseInterface {
    fun invoke(): Theme
}

class GetThemeUseCase @Inject constructor(
    private val preferences: SharedPreferences
) : GetThemeUseCaseInterface {

    override fun invoke(): Theme {
        val themeString = preferences.getString(THEME_PREFERENCE, Theme.IRON_HAND.toString())

        return Theme.entries.firstOrNull { it.toString() == themeString } ?: Theme.IRON_HAND
    }

}