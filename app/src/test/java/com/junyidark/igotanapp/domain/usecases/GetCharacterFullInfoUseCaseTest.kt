package com.junyidark.igotanapp.domain.usecases

import com.google.common.truth.Truth
import com.junyidark.igotanapp.domain.models.Character
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.models.Quote
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharacterFullInfoUseCaseTest {

    @Mock
    private lateinit var charactersRepository: CharactersRepositoryInterface

    private lateinit var useCase: GetCharacterFullInfoUseCaseInterface

    @Before
    fun setup() {
        useCase = GetCharacterFullInfoUseCase(charactersRepository)
    }

    @Test
    fun `WHEN invoke AND result is success THEN should return result`() = runBlocking {
        // Configuration
        val basics = CharacterBasics("url", "Jon Snow", "King of the North", "Stark")
        val details = CharacterDetails(House(-1, "Stark", emptyList()), listOf(Quote("Winter is coming")))
        Mockito.`when`(charactersRepository.getCharacterDetails("jon")).thenReturn(Result.success(details))

        val expectedResult = Result.success(
            Character(
                "url",
                "Jon Snow",
                "King of the North",
                House(-1, "Stark", emptyList()),
                listOf(Quote("Winter is coming"))
            )
        )

        // Execution
        val result = useCase.invoke(basics)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN invoke AND result is error THEN should return result`() = runBlocking {
        // Configuration
        val basics = CharacterBasics("url", "Jon Snow", "King of the North", "Stark")
        val exception = Exception()
        Mockito.`when`(charactersRepository.getCharacterDetails("jon")).thenReturn(Result.failure(exception))

        val expectedResult = Result.failure<Character>(exception)

        // Execution
        val result = useCase.invoke(basics)

        // Assertion
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

}