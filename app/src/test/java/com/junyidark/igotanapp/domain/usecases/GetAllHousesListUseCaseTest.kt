package com.junyidark.igotanapp.domain.usecases

import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllHousesListUseCaseTest {

    @Mock
    private lateinit var housesRepository: HousesRepositoryInterface

    private lateinit var useCase: GetAllHousesListUseCaseInterface

    @Before
    fun setup() {
        useCase = GetAllHousesListUseCase(housesRepository)
    }

    @Test
    fun `WHEN invoke THEN should return result`() = runBlocking {
        // Configuration
        val expectedResult = Result.success<List<House>>(listOf())
        Mockito.`when`(housesRepository.getAllHouses()).thenReturn(expectedResult)

        // Execution
        val result = useCase.invoke()

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}