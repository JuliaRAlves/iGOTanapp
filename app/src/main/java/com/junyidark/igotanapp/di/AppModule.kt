package com.junyidark.igotanapp.di

import com.junyidark.igotanapp.data.apis.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.apis.GameOfThronesQuotesApi
import com.junyidark.igotanapp.data.apis.ThronesApi
import com.junyidark.igotanapp.data.repositories.CharactersRepository
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCase
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCaseInterface
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
    abstract fun bindCharactersRepository(
        charactersRepository: CharactersRepository
    ): CharactersRepositoryInterface

    @Binds
    abstract fun bindCharactersBasicsApi(
        characterBasicsApi: ThronesApi
    ): CharacterBasicsApiInterface

    @Binds
    abstract fun bindCharactersDetailsApi(
        characterBasicsApi: GameOfThronesQuotesApi
    ): CharacterDetailsApiInterface
}

@Module
@InstallIn(ActivityComponent::class)
abstract class AppActivityModule {

    @Binds
    abstract fun bindRouter(router: Router): RouterInterface

}