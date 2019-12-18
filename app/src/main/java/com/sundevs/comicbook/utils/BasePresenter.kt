package com.sundevs.comicbook.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.sundevs.comicbook.view.BaseView
import com.sundevs.domain.models.CoroutinesContextProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

interface BasePresenter<T : BaseView> : IErrorProcessor, LifecycleObserver {

    var view: T?

    val subscriptions: CompositeDisposable?

    fun bind(view: T) {
        this.view = view
        errorHandler = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe() {
        view = null
        errorHandler = null
        subscriptions?.clear()
    }
}

interface BaseCoroutinePresenter<T : BaseView> : BasePresenter<T> {

    val parentJob: Job

    val coroutinesContextProvider: CoroutinesContextProvider

    fun launchJobOnMainDispatchers(job: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            job()
        }
    }

    fun <T> launchJob(
        backgroundJob: suspend CoroutineScope.() -> T,
        syncJob: (T) -> Unit,
        errorHandler: (Throwable) -> Unit = ::handleException
    ) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            try {
                val response = withContext(coroutinesContextProvider.backgroundContext) {
                    backgroundJob()
                }
                syncJob(response)
            } catch (t: Throwable) {
                errorHandler(t)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun unsubscribe() {
        super.unsubscribe()
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}
