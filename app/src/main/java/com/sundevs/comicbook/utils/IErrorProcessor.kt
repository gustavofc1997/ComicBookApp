package com.sundevs.comicbook.utils

import android.util.Log
import com.sundevs.comicbook.R
import com.sundevs.data.utils.NO_INTERNET_CONNECTION
import com.sundevs.domain.exceptions.NetworkException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

interface IErrorProcessor {

    var errorHandler: IErrorHandler?

    fun handleException(error: Throwable) {

        error.printStackTrace()

        when (error) {
            is SocketTimeoutException,
            is ConnectException -> {
                if (NO_INTERNET_CONNECTION == error.message) {
                    errorHandler?.showErrorDialog(R.string.snackbar_no_internet_connection)
                } else {
                    errorHandler?.showErrorDialog(R.string.connectivity_error_snackbar)
                }
            }
            is IllegalStateException -> Log.d(IErrorProcessor::class.java.name, error.message)
            is UnknownHostException -> handleHttpException(NetworkException.UnknowError(error.message))
            is NetworkException -> handleHttpException(error)
            is HttpException -> errorHandler?.showError(R.string.snackbar_unknown_error)
            is SSLHandshakeException -> errorHandler?.showError(
                R.string.connectivity_error_snackbar
            )
            else -> errorHandler?.showError(R.string.snackbar_unknown_error)
        }
    }

    private fun handleHttpException(error: NetworkException) {
        when (error) {
            is NetworkException.UnknowHost,
            is NetworkException.ServerError,
            is NetworkException.BadRequest -> {
                error.message?.let {
                    errorHandler?.showError(it)
                } ?: errorHandler?.showError(R.string.connectivity_error_snackbar)
            }

            is NetworkException.Forbidden -> {
                errorHandler?.showError(R.string.not_enough_permissions)
            }

            else -> errorHandler?.showError(R.string.snackbar_unknown_error)
        }
    }
}
