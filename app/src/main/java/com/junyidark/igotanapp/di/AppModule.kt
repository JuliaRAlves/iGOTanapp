package com.junyidark.igotanapp.di

import android.content.ClipboardManager
import android.content.Context
import com.junyidark.igotanapp.data.apis.GameOfThronesQuotesApi
import com.junyidark.igotanapp.data.apis.GameOfThronesQuotesApiServices
import com.junyidark.igotanapp.data.apis.ThronesApi
import com.junyidark.igotanapp.data.apis.ThronesApiServices
import com.junyidark.igotanapp.data.apis.interfaces.CharacterBasicsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.CharacterDetailsApiInterface
import com.junyidark.igotanapp.data.apis.interfaces.HousesApiInterface
import com.junyidark.igotanapp.data.repositories.CharactersRepository
import com.junyidark.igotanapp.data.repositories.HousesRepository
import com.junyidark.igotanapp.domain.repositories.CharactersRepositoryInterface
import com.junyidark.igotanapp.domain.repositories.HousesRepositoryInterface
import com.junyidark.igotanapp.domain.usecases.*
import com.junyidark.igotanapp.presentation.navigation.Router
import com.junyidark.igotanapp.presentation.navigation.RouterInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
}

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Singleton
    @Provides
    fun providesClipboardManager(@ApplicationContext context: Context): ClipboardManager {
        return context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppViewModelModule {

    companion object {
        private const val APP_SHARED_PREFERENCES = "APP_SHARED_PREFERENCES"

        @Provides
        fun providesSharedPreferences(@ApplicationContext context: Context) =
            context.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)

        @Provides
        fun providesThronesApiServices(retrofit: Retrofit.Builder): ThronesApiServices {
            return retrofit.baseUrl("https://thronesapi.com/")
                .build()
                .create(ThronesApiServices::class.java)
        }

        @Provides
        fun providesGameOfThronesQuotesApiServices(retrofit: Retrofit.Builder): GameOfThronesQuotesApiServices {
            return retrofit.baseUrl("https://api.gameofthronesquotes.xyz/")
                .build()
                .create(GameOfThronesQuotesApiServices::class.java)
        }
    }

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
    abstract fun bindSetThemesUseCase(
        setThemeUseCase: SetThemeUseCase
    ): SetThemeUseCaseInterface

    @Binds
    abstract fun bindGetThemesUseCase(
        getThemeUseCase: GetThemeUseCase
    ): GetThemeUseCaseInterface

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