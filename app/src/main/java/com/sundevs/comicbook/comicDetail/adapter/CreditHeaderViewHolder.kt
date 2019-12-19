package com.sundevs.comicbook.comicDetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sundevs.comicbook.R

class CreditHeaderViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    constructor(parent: ViewGroup)
            : this(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_credit_header,
            parent,
            false
        )
    )

    fun bind(header: String) {
        setupView(header)
    }

    private val tvCreditName = itemView.findViewById<TextView>(R.id.tvCreditHeader)

    private fun setupView(
        header: String
    ) {
        tvCreditName.text = header
    }
}