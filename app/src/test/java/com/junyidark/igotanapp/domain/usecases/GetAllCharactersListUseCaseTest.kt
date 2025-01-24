package com.junyidark.igotanapp.domain.usecases

import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllCharactersListUseCaseTest {

    @Mock
    private lateinit var charactersRepository: CharactersRepositoryInterface

    private lateinit var useCase: GetAllCharactersListUseCaseInterface

    @Before
    fun setup() {
        useCase = GetAllCharactersListUseCase(charactersRepository)
    }

    @Test
    fun `WHEN invoke THEN should return result`() = runBlocking {
        // Configuration
        val expectedResult = Result.success<List<CharacterBasics>>(listOf())
        Mockito.`when`(charactersRepository.getAllCharactersBasics()).thenReturn(expectedResult)

        // Execution
        val result = useCase.invoke()

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}