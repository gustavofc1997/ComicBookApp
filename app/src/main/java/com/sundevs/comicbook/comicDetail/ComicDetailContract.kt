package com.sundevs.comicbook.comicDetail

import android.os.Bundle
import com.sundevs.comicbook.utils.BaseCoroutinePresenter
import com.sundevs.comicbook.view.BaseView
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail

interface ComicDetailContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun setComic(comic: ComicDetail)
    }

    interface Presenter : BaseCoroutinePresenter<View> {
        fun getComicDetail()
        fun setComicUrl(comicUrl: String)
    }
}