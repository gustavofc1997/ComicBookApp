package com.sundevs.data.di

import android.content.Context
import com.sundevs.data.helpers.INetworkHelper
import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.NetworkClientImpl
import com.sundevs.data.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {

    @Provides
    @Singleton
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(context: Context): INetworkHelper = NetworkHelper(context)
}