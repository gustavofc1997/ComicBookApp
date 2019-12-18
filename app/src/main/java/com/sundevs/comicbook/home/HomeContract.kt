package com.sundevs.comicbook.home

import com.sundevs.comicbook.utils.BaseCoroutinePresenter
import com.sundevs.comicbook.view.BaseView
import com.sundevs.domain.models.Comic

interface HomeContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun setComicList(comics: ArrayList<Comic>)
    }

    interface Presenter : BaseCoroutinePresenter<View> {
        fun getComics()
        fun setComicList(comics: ArrayList<Comic>? = null)
    }
}