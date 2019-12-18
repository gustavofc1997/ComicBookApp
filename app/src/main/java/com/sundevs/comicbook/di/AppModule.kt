package com.sundevs.comicbook.di

import com.sundevs.domain.models.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProvider(Dispatchers.Main, Dispatchers.IO)
    }

}
