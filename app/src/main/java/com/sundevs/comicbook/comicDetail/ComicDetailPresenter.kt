package com.sundevs.comicbook.comicDetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.sundevs.comicbook.utils.ARGUMENT_COMIC_DETAIL
import com.sundevs.comicbook.utils.IErrorHandler
import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.models.CoroutinesContextProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class ComicDetailPresenter(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getDetailInteractor: Interactor<ComicDetail, String>
) : ComicDetailContract.Presenter {
    override fun setComicUrl(comicUrl: String) {
        this.comicUrl = comicUrl
    }

    override val parentJob: Job = Job()
    override var errorHandler: IErrorHandler? = null
    override var view: ComicDetailContract.View? = null
    override val subscriptions: CompositeDisposable? = CompositeDisposable()
    private var comicUrl: String? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun getComicDetail() {
        comicUrl?.let {
            view?.showLoading()
            launchJobOnMainDispatchers {
                try {
                    val comicDetail = withContext(coroutinesContextProvider.backgroundContext) {
                        getDetailInteractor.invoke(it)
                    }
                    view?.hideLoading()
                    view?.setComic(comicDetail)
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }

    }

}