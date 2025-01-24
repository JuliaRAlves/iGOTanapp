package com.junyidark.igotanapp.domain.usecases

import android.content.SharedPreferences
import com.junyidark.igotanapp.presentation.theme.THEME_PREFERENCE
import com.junyidark.igotanapp.presentation.theme.Theme
import javax.inject.Inject

interface SetThemeUseCaseInterface {
    fun invoke()
}

class SetThemeUseCase @Inject constructor(
    private val preferences: SharedPreferences
) : SetThemeUseCaseInterface {

    override fun invoke() {
        val currentThemeString = preferences.getString(THEME_PREFERENCE, Theme.IRON_HAND.toString())
        val currentTheme = Theme.entries.firstOrNull { it.toString() == currentThemeString } ?: Theme.IRON_HAND

        val newTheme = when (currentTheme) {
            Theme.INTO_THE_SUN -> Theme.DEEP_RIVERS
            Theme.DEEP_RIVERS -> Theme.IRON_HAND
            Theme.IRON_HAND -> Theme.INTO_THE_SUN
        }

        val editor = preferences.edit()

        editor.putString(THEME_PREFERENCE, newTheme.toString())
        editor.apply()
    }

}