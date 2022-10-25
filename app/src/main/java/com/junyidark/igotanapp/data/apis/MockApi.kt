package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse
import retrofit2.Response

class MockApi : CharacterBasicsApiInterface, CharacterDetailsApiInterface, HousesApiInterface {
    override fun getAllCharactersBasics(): Response<List<CharacterBasicsResponse>> {
        return Response.success(
            listOf(
                CharacterBasicsResponse(
                    fullName = "Jon Snow",
                    title = "King of the North",
                    imageUrl = "https://static.wikia.nocookie.net/gameofthrones/images/d/da/Jon_Snow_perfil_2.jpg/revision/latest?cb=20220217225407&path-prefix=pt-br",
                    house = "House Stark"
                ),
                CharacterBasicsResponse(
                    fullName = "Jon Snow",
                    title = "King of the North",
                    imageUrl = "https://static.wikia.nocookie.net/gameofthrones/images/d/da/Jon_Snow_perfil_2.jpg/revision/latest?cb=20220217225407&path-prefix=pt-br",
                    house = "House Stark"
                ),
                CharacterBasicsResponse(
                    fullName = "Jon Snow",
                    title = "King of the North",
                    imageUrl = "https://static.wikia.nocookie.net/gameofthrones/images/d/da/Jon_Snow_perfil_2.jpg/revision/latest?cb=20220217225407&path-prefix=pt-br",
                    house = "House Stark"
                )
            )
        )
    }

    override fun getCharacterDetails(firstName: String): Result<CharacterDetailsResponse> {
        return Result.success(
            CharacterDetailsResponse(
                house = HouseResponse(
                    name = "House Stark of Winterfell",
                    members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
                ),
                quotes = listOf(
                    "You know nothing, Jon Snow",
                    "You know nothing, Jon Snow",
                    "You know nothing, Jon Snow"
                )
            )
        )
    }

    override fun getAllHouses(): Result<List<HouseResponse>> {
        return Result.success(
            listOf(
                HouseResponse(
                    name = "House Stark of Winterfell",
                    members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
                ),
                HouseResponse(
                    name = "House Stark of Winterfell",
                    members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
                ),
                HouseResponse(
                    name = "House Stark of Winterfell",
                    members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
                ),
                HouseResponse(
                    name = "House Stark of Winterfell",
                    members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
                )
            )
        )
    }
}