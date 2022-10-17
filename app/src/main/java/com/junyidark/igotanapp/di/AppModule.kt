package com.junyidark.igotanapp.di

import com.junyidark.igotanapp.data.apis.*
import com.junyidark.igotanapp.data.repositories.CharactersRepository
import com.junyidark.igotanapp.data.repositories.HousesRepository
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.*
import com.junyidark.igotanapp.presentation.navigation.Router
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppViewModelModule {

    @Binds
    abstract fun bindSearchCharacterByNameUseCase(
        searchCharacterByNameUseCase: SearchCharacterByNameUseCase
    ): SearchCharacterByNameUseCaseInterface

    @Binds
    abstract fun bindGetAllCharactersUseCase(
        getAllCharactersListUseCase: GetAllCharactersListUseCase
    ): GetAllCharactersListUseCaseInterface

    @Binds
    abstract fun bindGetCharacterFullInfoUseCase(
        getCharacterFullInfoUseCase: GetCharacterFullInfoUseCase
    ): GetCharacterFullInfoUseCaseInterface

    @Binds
    abstract fun bindGetAllHousesUseCase(
        getAllHousesListUseCase: GetAllHousesListUseCase
    ): GetAllHousesListUseCaseInterface

    @Binds
    abstract fun bindGetHouseCoatOfArmsUseCase(
        getHouseCoatOfArmsUseCase: GetHouseCoatOfArmsUseCase
    ): GetHouseCoatOfArmsUseCaseInterface

    @Binds
    abstract fun bindCharactersRepository(
        charactersRepository: CharactersRepository
    ): CharactersRepositoryInterface

    @Binds
    abstract fun bindHousesRepository(
        housesRepository: HousesRepository
    ): HousesRepositoryInterface

    @Binds
    abstract fun bindCharactersBasicsApi(
        characterBasicsApi: ThronesApi
    ): CharacterBasicsApiInterface

    @Binds
    abstract fun bindCharactersDetailsApi(
        characterDetailsApi: GameOfThronesQuotesApi
    ): CharacterDetailsApiInterface

    @Binds
    abstract fun bindHousesApi(
        housesApi: GameOfThronesQuotesApi
    ): HousesApiInterface
}

@Module
@InstallIn(ActivityComponent::class)
abstract class AppActivityModule {

    @Binds
    abstract fun bindRouter(router: Router): RouterInterface

}