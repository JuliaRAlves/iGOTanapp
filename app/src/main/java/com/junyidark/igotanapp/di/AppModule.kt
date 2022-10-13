package com.junyidark.igotanapp.di

import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCase
import com.junyidark.igotanapp.domain.usecases.SearchCharacterByNameUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindSearchCharacterByNameUseCase(
        searchCharacterByNameUseCase: SearchCharacterByNameUseCase
    ): SearchCharacterByNameUseCaseInterface

}