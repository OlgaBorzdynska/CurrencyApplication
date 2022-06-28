package com.example.currencyapplication.di



import com.example.currencyapplication.api.FixerApi
import com.example.currencyapplication.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFixerApi() : FixerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_FIXER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FixerApi::class.java)
    }
}