package com.sundevs.comicbook.di

import android.content.Context
import com.sundevs.comicbook.ComicsBookApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class),
        (AppModule::class),
        (ActivityBuilder::class),
        (InteractorsModule::class)]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }

    fun inject(comicsBookApplication: ComicsBookApplication)

}