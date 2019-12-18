package com.sundevs.comicbook.home

import android.os.Bundle
import android.view.View
import com.sundevs.comicbook.R
import com.sundevs.comicbook.home.adapter.ComicsAdapter
import com.sundevs.comicbook.home.adapter.ComicsAdapterListener
import com.sundevs.comicbook.view.BaseActivity
import com.sundevs.domain.models.Comic
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager

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
                this@HomeActivity, 3
            )
        comicsAdapter = ComicsAdapter(this, defaultlayoutManager)

        rvComics?.apply {
            adapter = comicsAdapter
            layoutManager = defaultlayoutManager
            setHasFixedSize(false)
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setComicList(comics: ArrayList<Comic>) {
        comicsAdapter.setComics(comics)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val wrappedMenu = menu ?: return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.home_menu, wrappedMenu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onComicClicked(comic: Comic, view: View) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_grid -> defaultlayoutManager.spanCount = 3
            R.id.action_list -> defaultlayoutManager.spanCount = 1
        }
        (rvComics.adapter as ComicsAdapter).let {
            it.notifyItemRangeChanged(0, it.itemCount)
        }
        return super.onOptionsItemSelected(item)
    }

}