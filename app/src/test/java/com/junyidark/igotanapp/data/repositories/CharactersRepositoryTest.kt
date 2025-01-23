package com.junyidark.igotanapp.data.repositories

import com.google.common.truth.Truth
import com.junyidark.igotanapp.data.apis.interfaces.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.domain.models.CharacterBasics
import com.junyidark.igotanapp.domain.models.CharacterDetails
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.models.Quote
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryTest {

    @Mock
    private lateinit var basicsApi: CharacterBasicsApiInterface

    @Mock
    private lateinit var detailsApi: CharacterDetailsApiInterface

    private lateinit var repository: CharactersRepositoryInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CharactersRepository(basicsApi, detailsApi)
    }

    @Test
    fun `WHEN getAllCharactersBasics AND is success THEN should return success`() = runBlocking {
        // Configuration
        val responseList = listOf(
            CharacterBasicsResponse("Jon Snow", "King of The North", "url", "Stark"),
            CharacterBasicsResponse("Daenerys Targaryen", "Queen of Dragons", "url", "Targaryen")
        )
        val expectedResponse = Response.success(responseList)

        Mockito.`when`(basicsApi.getAllCharactersBasics()).thenReturn(expectedResponse)

        val expectedResult = listOf(
            CharacterBasics("url", "Jon Snow", "King of The North", "Stark"),
            CharacterBasics("url", "Daenerys Targaryen", "Queen of Dragons", "Targaryen")
        )

        // Execution
        val result = repository.getAllCharactersBasics()

        // Assertion
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN getAllCharactersBasics AND is error THEN should return error`() = runBlocking {
        // Configuration
        val responseBody = object : ResponseBody() {
            override fun contentLength(): Long {
                return 0
            }

            override fun contentType(): MediaType? {
                return null
            }

            override fun source(): BufferedSource {
                TODO()
            }
        }
        val expectedResponse = Response.error<List<CharacterBasicsResponse>>(404, responseBody)

        Mockito.`when`(basicsApi.getAllCharactersBasics()).thenReturn(expectedResponse)

        // Execution
        val result = repository.getAllCharactersBasics()

        // Assertion
        Truth.assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `WHEN getCharacterDetails AND is success THEN should return success`() = runBlocking {
        // Configuration
        val firstName = "Jon"
        val responseList = listOf(
            CharacterDetailsResponse(HouseResponse("Stark"), listOf("Winter is coming"))
        )
        val expectedResponse = Response.success(responseList)

        Mockito.`when`(detailsApi.getCharacterDetails(firstName)).thenReturn(expectedResponse)

        val expectedResult = CharacterDetails(House(-1, "Stark", emptyList()), listOf(Quote("Winter is coming")))

        // Execution
        val result = repository.getCharacterDetails(firstName)

        // Assertion
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN getCharacterDetails AND is error THEN should return error`() = runBlocking {
        // Configuration
        val firstName = "Jon"
        val responseBody = object : ResponseBody() {
            override fun contentLength(): Long {
                return 0
            }

            override fun contentType(): MediaType? {
                return null
            }

            override fun source(): BufferedSource {
                TODO()
            }
        }
        val expectedResponse = Response.error<List<CharacterDetailsResponse>>(404, responseBody)

        Mockito.`when`(detailsApi.getCharacterDetails(firstName)).thenReturn(expectedResponse)

        // Execution
        val result = repository.getCharacterDetails(firstName)

        // Assertion
        Truth.assertThat(result.isFailure).isTrue()
    }

}