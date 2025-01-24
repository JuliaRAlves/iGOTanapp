package com.junyidark.igotanapp.domain.usecases

import android.content.SharedPreferences
import com.google.common.truth.Truth
import com.junyidark.igotanapp.presentation.theme.Theme
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetThemeUseCaseTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var useCase: GetThemeUseCaseInterface

    @Before
    fun setup() {
        useCase = GetThemeUseCase(sharedPreferences)
    }

    @Test
    fun `WHEN invoke AND has saved theme THEN should return saved theme`() {
        // Configuration
        val expectedResult = Theme.DEEP_RIVERS
        Mockito.`when`(sharedPreferences.getString("THEME_PREFERENCE", "IRON_HAND")).thenReturn("DEEP_RIVERS")

        // Execution
        val result = useCase.invoke()

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND has no saved theme THEN should return default theme`() {
        // Configuration
        val expectedResult = Theme.IRON_HAND
        Mockito.`when`(sharedPreferences.getString("THEME_PREFERENCE", "IRON_HAND")).thenReturn(null)

        // Execution
        val result = useCase.invoke()

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}