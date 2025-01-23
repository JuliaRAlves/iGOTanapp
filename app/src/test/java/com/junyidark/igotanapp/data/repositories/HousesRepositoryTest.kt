package com.junyidark.igotanapp.data.repositories

import com.google.common.truth.Truth
import com.junyidark.igotanapp.data.apis.interfaces.HousesApiInterface
import com.junyidark.igotanapp.data.models.HouseResponse
import com.junyidark.igotanapp.data.models.MemberResponse
import com.junyidark.igotanapp.domain.models.House
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.GetHouseCoatOfArmsUseCaseInterface
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
class HousesRepositoryTest {

    @Mock
    private lateinit var housesApi: HousesApiInterface

    @Mock
    private lateinit var armsUseCase: GetHouseCoatOfArmsUseCaseInterface

    private lateinit var repository: HousesRepositoryInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = HousesRepository(housesApi, armsUseCase)
    }

    @Test
    fun `WHEN getAllHouses AND is success THEN should return success`() = runBlocking {
        // Configuration
        val listResponse = listOf(
            HouseResponse("Stark", listOf(MemberResponse("Jon Snow"))),
            HouseResponse("Targaryen", listOf(MemberResponse("Daenerys Targaryen")))
        )
        val expectedResponse = Response.success(listResponse)
        Mockito.`when`(housesApi.getAllHouses()).thenReturn(expectedResponse)
        Mockito.`when`(armsUseCase.invoke("Stark")).thenReturn(1)
        Mockito.`when`(armsUseCase.invoke("Targaryen")).thenReturn(2)

        val expectedResult = listOf(
            House(1, "Stark", listOf("Jon Snow")),
            House(2, "Targaryen", listOf("Daenerys Targaryen"))
        )

        // Execution
        val result = repository.getAllHouses()

        // Assertion
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()).isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN getAllHouses AND is error THEN should return error`() = runBlocking {
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
        val expectedResponse = Response.error<List<HouseResponse>>(404, responseBody)

        Mockito.`when`(housesApi.getAllHouses()).thenReturn(expectedResponse)

        // Execution
        val result = repository.getAllHouses()

        // Assertion
        Truth.assertThat(result.isFailure).isTrue()
    }

}