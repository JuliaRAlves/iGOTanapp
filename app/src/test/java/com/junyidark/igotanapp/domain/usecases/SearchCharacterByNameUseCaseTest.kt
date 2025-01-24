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
class SearchCharacterByNameUseCaseTest {

    @Mock
    private lateinit var charactersRepository: CharactersRepositoryInterface

    private lateinit var useCase: SearchCharacterByNameUseCaseInterface

    @Before
    fun setup() {
        useCase = SearchCharacterByNameUseCase(charactersRepository)
    }

    @Test
    fun `WHEN invoke AND result is success THEN should return result`() = runBlocking {
        // Configuration
        val query = "ar"
        val allCharactersResult = Result.success(
            listOf(
                CharacterBasics("url", "Jon Snow", "King of the North", "Stark"),
                CharacterBasics("url", "Arya Stark", "No one", "Stark"),
                CharacterBasics("url", "Daenerys Targaryen", "Mother of dragons", "Targaryen")
            )
        )
        Mockito.`when`(charactersRepository.getAllCharactersBasics()).thenReturn(allCharactersResult)
        val expectedResult = Result.success(
            listOf(
                CharacterBasics("url", "Arya Stark", "No one", "Stark"),
                CharacterBasics("url", "Daenerys Targaryen", "Mother of dragons", "Targaryen")
            )
        )

        // Execution
        val result = useCase.invoke(query)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND result is error THEN should return result`() = runBlocking {
        // Configuration
        val query = "ar"
        val expectedResult = Result.failure<List<CharacterBasics>>(Exception())
        Mockito.`when`(charactersRepository.getAllCharactersBasics()).thenReturn(expectedResult)

        // Execution
        val result = useCase.invoke(query)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}