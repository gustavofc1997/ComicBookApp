package com.sundevs.comicbook.di

import com.sundevs.comicbook.comicDetail.ComicDetailActivity
import com.sundevs.comicbook.comicDetail.ComicDetailModule
import com.sundevs.comicbook.home.HomeActivity
import com.sundevs.comicbook.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class)])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [(ComicDetailModule::class)])
    abstract fun bindComicDetailActivity(): ComicDetailActivity
}
