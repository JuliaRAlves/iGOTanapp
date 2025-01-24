package com.junyidark.igotanapp.domain.usecases

import com.google.common.truth.Truth
import com.junyidark.igotanapp.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GeHouseCoatOfArmsUseCaseTest {

    private lateinit var useCase: GetHouseCoatOfArmsUseCaseInterface

    @Before
    fun setup() {
        useCase = GetHouseCoatOfArmsUseCase()
    }

    @Test
    fun `WHEN invoke AND is house stark THEN should return resource`() {
        // Configuration
        val houseName = "House Stark of Winterfell"
        val expectedResult = R.drawable.house_stark

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house lannister THEN should return resource`() {
        // Configuration
        val houseName = "House Lannister of Casterly Rock"
        val expectedResult = R.drawable.house_lannister

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house baratheon THEN should return resource`() {
        // Configuration
        val houseName = "House Baratheon of Dragonstone"
        val expectedResult = R.drawable.house_baratheon

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house targaryen THEN should return resource`() {
        // Configuration
        val houseName = "House Targaryen of King's Landing"
        val expectedResult = R.drawable.house_targaryen

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house greyjoy THEN should return resource`() {
        // Configuration
        val houseName = "House Greyjoy of Pyke"
        val expectedResult = R.drawable.house_greyjoy

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house tarly THEN should return resource`() {
        // Configuration
        val houseName = "House Tarly of Horn Hill"
        val expectedResult = R.drawable.house_tarly

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house bolton THEN should return resource`() {
        // Configuration
        val houseName = "House Bolton of the Dreadfort"
        val expectedResult = R.drawable.house_bolton

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house baelish THEN should return resource`() {
        // Configuration
        val houseName = "House Baelish of Harrenhal"
        val expectedResult = R.drawable.house_baelish

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house tyrell THEN should return resource`() {
        // Configuration
        val houseName = "House Tyrell of Highgarden"
        val expectedResult = R.drawable.house_tyrell

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house clegane THEN should return resource`() {
        // Configuration
        val houseName = "House Clegane of Clegane's Keep"
        val expectedResult = R.drawable.house_clegane

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house frey THEN should return resource`() {
        // Configuration
        val houseName = "House Frey of the Crossing"
        val expectedResult = R.drawable.house_frey

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house martell THEN should return resource`() {
        // Configuration
        val houseName = "House Martell of Sunspear"
        val expectedResult = R.drawable.house_martell

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is house mormont THEN should return resource`() {
        // Configuration
        val houseName = "House Mormont of Bear Island"
        val expectedResult = R.drawable.house_mormont

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND is none of the known houses THEN should return resource`() {
        // Configuration
        val houseName = "House Generic"
        val expectedResult = R.drawable.house_generic

        // Execution
        val result = useCase.invoke(houseName)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}