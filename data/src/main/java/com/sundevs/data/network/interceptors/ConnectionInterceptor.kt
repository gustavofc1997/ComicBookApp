package com.sundevs.data.network.interceptors

import com.sundevs.data.helpers.INetworkHelper
import com.sundevs.data.utils.NO_INTERNET_CONNECTION
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException

class ConnectionInterceptor(
    private val mNetworkHelper: INetworkHelper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return if (mNetworkHelper.isInternetAvailable()) {
            chain.proceed(originalRequest)
        } else
            throw ConnectException(NO_INTERNET_CONNECTION)
    }
}
