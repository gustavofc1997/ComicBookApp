package com.sundevs.comicbook.home.adapter

import android.view.View
import com.sundevs.domain.models.Comic

interface ComicsAdapterListener {

    fun onComicClicked(
        comic: Comic,
        view: View
    )
}