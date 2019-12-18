package com.sundevs.comicbook

import android.content.Context
import androidx.multidex.MultiDex
import com.sundevs.comicbook.di.AppComponent
import com.sundevs.comicbook.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ComicsBookApplication : DaggerApplication() {

    var mAppComponent: AppComponent? = null
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .application(this)
            .build()
        mAppComponent = component
        component.inject(this)
        return component
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}