package com.sundevs.data.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sundevs.data.helpers.INetworkHelper
import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.NetworkClientImpl
import com.sundevs.data.network.endpoints.ComicService
import com.sundevs.data.network.interceptors.AuthorizationInterceptor
import com.sundevs.data.network.interceptors.ConnectionInterceptor
import com.sundevs.data.utils.SERVICE_BASE_URL
import com.sundevs.data.utils.TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(HelperModule::class)])
class ServiceProviderModule {

    @Provides
    @Singleton
    fun provideBaseClient(
        networkHelper: INetworkHelper
    ):
            OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(ConnectionInterceptor(networkHelper))
            .addInterceptor(AuthorizationInterceptor())
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideBaseRetrofit(
        baseOkHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(baseOkHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideComicService(retrofit: Retrofit): ComicService {
        return retrofit.create(ComicService::class.java)
    }
}
