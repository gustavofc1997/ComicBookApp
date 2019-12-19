package com.sundevs.comicbook.comicDetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sundevs.comicbook.R
import com.sundevs.domain.models.ComicCredit

class CreditViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false))

    fun bind(comic: ComicCredit) {
        setupView(comic)
    }

    private val tvCreditName = itemView.findViewById<TextView>(R.id.tvCreditTitle)

    private fun setupView(
        comic: ComicCredit
    ) {
        tvCreditName.text = comic.name
    }
}