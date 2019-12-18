package com.sundevs.data.di

import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.endpoints.ComicService
import com.sundevs.data.repositories.ComicsRepositoryImpl
import com.sundevs.domain.repositories.IComicsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServiceProviderModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providesComicsRepository(
        networkClient: NetworkClient,
        comicsService: ComicService
    ): IComicsRepository {
        return ComicsRepositoryImpl(networkClient, comicsService)
    }
}