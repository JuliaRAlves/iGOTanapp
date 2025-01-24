package com.junyidark.igotanapp.domain.usecases

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SetThemeUseCaseTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: Editor

    private lateinit var useCase: SetThemeUseCaseInterface

    @Before
    fun setup() {
        useCase = SetThemeUseCase(sharedPreferences)
    }

    @Test
    fun `WHEN invoke AND current theme is INTO_THE_SUN THEN should set DEEP_RIVERS`() {
        // Configuration
        Mockito.`when`(sharedPreferences.getString("THEME_PREFERENCE", "IRON_HAND")).thenReturn("INTO_THE_SUN")
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)

        // Execution
        useCase.invoke()

        // Assertion
        Mockito.verify(editor).putString("THEME_PREFERENCE", "DEEP_RIVERS")
    }

    @Test
    fun `WHEN invoke AND current theme is DEEP_RIVERS THEN should set IRON_HAND`() {
        // Configuration
        Mockito.`when`(sharedPreferences.getString("THEME_PREFERENCE", "IRON_HAND")).thenReturn("DEEP_RIVERS")
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)

        // Execution
        useCase.invoke()

        // Assertion
        Mockito.verify(editor).putString("THEME_PREFERENCE", "IRON_HAND")
    }

    @Test
    fun `WHEN invoke AND current theme is IRON_HAND THEN should set INTO_THE_SUN`() {
        // Configuration
        Mockito.`when`(sharedPreferences.getString("THEME_PREFERENCE", "IRON_HAND")).thenReturn("IRON_HAND")
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)

        // Execution
        useCase.invoke()

        // Assertion
        Mockito.verify(editor).putString("THEME_PREFERENCE", "INTO_THE_SUN")
    }

}