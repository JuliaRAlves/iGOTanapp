package com.junyidark.igotanapp.data.apis

import com.junyidark.igotanapp.data.models.CharacterBasicsResponse
import com.junyidark.igotanapp.data.models.CharacterDetailsResponse
import com.junyidark.igotanapp.data.models.HouseResponse

class MockApi : CharacterBasicsApiInterface, CharacterDetailsApiInterface, HousesApiInterface {
    override fun getAllCharactersBasics(): List<CharacterBasicsResponse> {
        return listOf(
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
    }

    override fun getCharacterDetails(): CharacterDetailsResponse {
        return CharacterDetailsResponse(
            house = HouseResponse(
                name = "House Stark of Winterfell",
                members = listOf("Jon Snow", "Arya Stark", "Sansa Stark")
            ),
            quotes = listOf("You know nothing, Jon Snow", "You know nothing, Jon Snow", "You know nothing, Jon Snow")
        )
    }

    override fun getAllHouses(): List<HouseResponse> {
        return listOf(
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
    }
}