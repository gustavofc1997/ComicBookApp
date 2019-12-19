package com.sundevs.comicbook.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.sundevs.comicbook.R
import com.sundevs.comicbook.comicDetail.ComicDetailActivity
import com.sundevs.comicbook.home.adapter.ComicsAdapter
import com.sundevs.comicbook.home.adapter.ComicsAdapterListener
import com.sundevs.comicbook.utils.ARGUMENT_COMIC_DETAIL
import com.sundevs.comicbook.utils.COUNT_GRID_MODE
import com.sundevs.comicbook.utils.COUNT_LIST_MODE
import com.sundevs.comicbook.view.BaseActivity
import com.sundevs.domain.models.Comic
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, ComicsAdapterListener {

    @Inject
    lateinit var presenter: HomeContract.Presenter
    private lateinit var defaultlayoutManager: GridLayoutManager
    private lateinit var comicsAdapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setSupportActionBar(toolbar)
        presenter.bind(this)
        lifecycle.addObserver(presenter)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        defaultlayoutManager =
            GridLayoutManager(
                this@HomeActivity, COUNT_GRID_MODE
            )
        comicsAdapter = ComicsAdapter(this, defaultlayoutManager)

        rvComics?.apply {
            adapter = comicsAdapter
            layoutManager = defaultlayoutManager
            setHasFixedSize(false)
        }
    }

    override fun showLoading() {
        showProgressDialog()
    }

    override fun hideLoading() {
        hideProgressDialog()
    }

    override fun setComicList(comics: ArrayList<Comic>) {
        comicsAdapter.setComics(comics)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val wrappedMenu = menu ?: return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.home_menu, wrappedMenu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onComicClicked(comic: Comic) {
        val intent = Intent(this, ComicDetailActivity::class.java).apply {
            putExtra(ARGUMENT_COMIC_DETAIL, comic.comicDetailUrl)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_grid -> defaultlayoutManager.spanCount = COUNT_GRID_MODE
            R.id.action_list -> defaultlayoutManager.spanCount = COUNT_LIST_MODE
        }
        (rvComics.adapter as ComicsAdapter).let {
            it.notifyItemRangeChanged(0, it.itemCount)
        }
        return super.onOptionsItemSelected(item)
    }
}