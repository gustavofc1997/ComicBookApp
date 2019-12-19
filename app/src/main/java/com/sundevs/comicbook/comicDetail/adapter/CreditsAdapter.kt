package com.sundevs.comicbook.comicDetail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sundevs.domain.models.ComicCredit

class CreditsAdapter(
    val header: String, var credits: List<ComicCredit>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreditViewHolder) {
            holder.bind((credits[position]))
        } else {
            (holder as CreditHeaderViewHolder).bind(header)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HEADER.ordinal -> CreditHeaderViewHolder(parent)
            else -> CreditViewHolder(parent)
        }
    }

    enum class ViewType {
        HEADER,
        ITEMS
    }
    override fun getItemCount() = credits.size

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) ViewType.HEADER.ordinal else ViewType.ITEMS.ordinal
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }
}
