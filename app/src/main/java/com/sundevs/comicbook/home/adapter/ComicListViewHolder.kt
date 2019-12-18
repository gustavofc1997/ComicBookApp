package com.sundevs.comicbook.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sundevs.comicbook.R
import com.sundevs.comicbook.utils.loadImageFromUrl
import com.sundevs.domain.models.Comic

class ComicListViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView), BindViewHolder {

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.item_comic_list, parent, false))

    private val ivComicLogo = itemView.findViewById<ImageView>(R.id.ivComic)
    private val tvComicTitle = itemView.findViewById<TextView>(R.id.tvComicTitle)
    private val tvComicDate = itemView.findViewById<TextView>(R.id.tvComicDate)

    override fun bind(workplace: Comic, listener: ComicsAdapterListener) {
        setupView(workplace, listener)
    }

    private fun setupView(
        comic: Comic, listener: ComicsAdapterListener
    ) {
        ivComicLogo.loadImageFromUrl(comic.image)
        tvComicDate.text = comic.dateAdded
        tvComicTitle.text = comic.name

        itemView.setOnClickListener {
            listener.onComicClicked(comic, it)
        }
    }
}