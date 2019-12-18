package com.sundevs.comicbook.home

import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.CoroutinesContextProvider
import com.sundevs.domain.models.None
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomePresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getComicsInteractor: Interactor<List<Comic>, None>
    ): HomeContract.Presenter {
        return HomePresenter(coroutinesContextProvider, getComicsInteractor)
    }
}