package com.sundevs.comicbook.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sundevs.domain.models.Comic

class ComicsAdapter(
    private val comicsAdapterListener: ComicsAdapterListener,
    private val layoutManager: GridLayoutManager? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var comics: List<Comic> = listOf()

    fun setComics(comics: List<Comic>) {
        this.comics = comics
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindViewHolder).bind(comics[position], comicsAdapterListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.GRID.ordinal -> ComicsGridViewHolder(parent)
            else -> ComicListViewHolder(parent)
        }
    }

    override fun getItemCount() = comics.size

    enum class ViewType {
        GRID,
        LIST
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }
}
