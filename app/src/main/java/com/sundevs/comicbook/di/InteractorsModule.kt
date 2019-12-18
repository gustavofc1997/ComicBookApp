package com.sundevs.comicbook.di

import com.sundevs.data.di.RepositoryModule
import com.sundevs.domain.interactors.GetComicsInteractor
import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.None
import com.sundevs.domain.repositories.IComicsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorsModule {
    @Singleton
    @Provides
    fun provideGetComicsInteractor(
        comicsRepository: IComicsRepository
    ): Interactor<List<Comic>, None> {
        return GetComicsInteractor(
            comicsRepository
        )
    }
}