package com.sundevs.comicbook.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sundevs.comicbook.R
import com.sundevs.comicbook.utils.loadImageFromUrl
import com.sundevs.domain.models.Comic

class ComicsGridViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), BindViewHolder {

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false))

    override fun bind(comic: Comic, listener: ComicsAdapterListener) {
        setupView(comic, listener)
    }

    private val ivComicLogo = itemView.findViewById<ImageView>(R.id.ivComic)
    private val tvComicTitle = itemView.findViewById<TextView>(R.id.tvComicTitle)
    private val tvComicDate = itemView.findViewById<TextView>(R.id.tvComicDate)

    private fun setupView(
        comic: Comic, listener: ComicsAdapterListener
    ) {
        ivComicLogo.loadImageFromUrl(comic.image)
        tvComicDate.text = comic.dateAdded
        tvComicTitle.text = comic.name

        itemView.setOnClickListener {
            listener.onComicClicked(comic)
        }
    }
}

interface BindViewHolder {
    fun bind(
        comic: Comic,
        listener: ComicsAdapterListener
    )
}