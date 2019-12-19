package com.sundevs.comicbook.comicDetail

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sundevs.comicbook.R
import com.sundevs.comicbook.comicDetail.adapter.CreditsAdapter
import com.sundevs.comicbook.utils.ARGUMENT_COMIC_DETAIL
import com.sundevs.comicbook.utils.COUNT_GRID_MODE
import com.sundevs.comicbook.utils.loadImageFromUrl
import com.sundevs.comicbook.view.BaseActivity
import com.sundevs.domain.models.ComicCredit
import com.sundevs.domain.models.ComicDetail
import kotlinx.android.synthetic.main.comic_detail_activity.*
import javax.inject.Inject


class ComicDetailActivity : BaseActivity(), ComicDetailContract.View {

    @Inject
    lateinit var presenter: ComicDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_detail_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        lifecycle.addObserver(presenter)
        presenter.bind(this)
        intent.extras?.let {
            it.getString(ARGUMENT_COMIC_DETAIL)?.let { url ->
                presenter.setComicUrl(url)
            }
        }
    }

    override fun showLoading() {
        showProgressDialog()
    }

    override fun hideLoading() {
        hideProgressDialog()
    }

    private fun createAdapter(header: String, list: List<ComicCredit>): CreditsAdapter {
        return CreditsAdapter(header, list)
    }

    private fun getGridLayoutManager() =
        LinearLayoutManager(
            this
        )

    override fun setComic(comic: ComicDetail) {
        ivComic.loadImageFromUrl(comic.image)

        comic.characterCredits?.let {
            rvCharacters?.apply {
                adapter = createAdapter("Characters", it)
                layoutManager = getGridLayoutManager()
                setHasFixedSize(false)
            }
        }
        comic.locationCredits?.let {
            rvLocation?.apply {
                adapter = createAdapter("Locations", it)
                layoutManager = getGridLayoutManager()
                setHasFixedSize(false)
            }
        }
        comic.teamCredits?.let {
            rvTeam?.apply {
                adapter = createAdapter("Team", it)
                layoutManager = getGridLayoutManager()
                setHasFixedSize(false)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}