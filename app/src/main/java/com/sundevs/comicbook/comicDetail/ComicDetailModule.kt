package com.sundevs.comicbook.comicDetail

import com.sundevs.comicbook.home.HomeContract
import com.sundevs.comicbook.home.HomePresenter
import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.models.CoroutinesContextProvider
import com.sundevs.domain.models.None
import dagger.Module
import dagger.Provides

@Module
class ComicDetailModule {

    @Provides
    fun providesComicDetailPresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getComicDetailInteractor: Interactor<ComicDetail, String>
    ): ComicDetailContract.Presenter {
        return ComicDetailPresenter(coroutinesContextProvider, getComicDetailInteractor)
    }
}