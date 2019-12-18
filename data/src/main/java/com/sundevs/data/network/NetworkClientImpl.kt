package com.sundevs.data.network

import com.google.gson.Gson
import com.sundevs.domain.exceptions.BackendErrorContent
import com.sundevs.domain.exceptions.NetworkException
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.HttpURLConnection

class NetworkClientImpl : NetworkClient {

    override suspend fun <T : Any> apiCall(call: Deferred<Response<T>>): Response<T> {
        val response = call.await()

        if (response.isSuccessful) {
            return response
        }

        val errorBody = response.errorBody()?.string()

        val error = getErrorFromResponse(
            errorBody
        )

        throw when (response.code()) {
            HttpURLConnection.HTTP_NOT_FOUND -> NetworkException.NotFound(error)
            HttpURLConnection.HTTP_NOT_ACCEPTABLE,
            HttpURLConnection.HTTP_BAD_REQUEST -> NetworkException.BadRequest(error)
            HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkException.ServerError(error)
            HttpURLConnection.HTTP_FORBIDDEN -> NetworkException.Unauthorized(error)
            else -> UnknownError(error)
        }
    }

    override fun getErrorFromResponse(responseAsString: String?): String? {

        val errorString = responseAsString?.let { it } ?: return null

        return try {
            Gson().fromJson(errorString, BackendErrorContent::class.java)
                .error
        } catch (e: Exception) {
            null
        }
    }
}