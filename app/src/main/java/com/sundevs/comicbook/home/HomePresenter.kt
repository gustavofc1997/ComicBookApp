package com.sundevs.comicbook.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.sundevs.comicbook.utils.IErrorHandler
import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.CoroutinesContextProvider
import com.sundevs.domain.models.None
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class HomePresenter(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getComicsInteractor: Interactor<List<Comic>, None>
) :
    HomeContract.Presenter {

    override val parentJob: Job = Job()
    override var errorHandler: IErrorHandler? = null
    override var view: HomeContract.View? = null
    override val subscriptions: CompositeDisposable? = CompositeDisposable()
    private var comicList: ArrayList<Comic>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun getComics() {
        view?.showLoading()
        launchJobOnMainDispatchers {
            try {
                val list = withContext(coroutinesContextProvider.backgroundContext) {
                    getComicsInteractor(None)
                } as ArrayList<Comic>
                setComicList(list)
            } catch (t: Throwable) {
                handleException(t)
                setComicList()
            }
        }
    }

    override fun setComicList(comics: ArrayList<Comic>?) {
        this.comicList = comics
        comicList?.let {
            view?.hideLoading()
            view?.setComicList(it)
        }
    }
}