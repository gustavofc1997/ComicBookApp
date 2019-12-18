package com.sundevs.comicbook.utils

interface IErrorHandler {

    fun showErrorDialog(errorStringId: Int)

    fun showErrorDialog(error: String)

    fun showError(error: String)

    fun showError(errorStringId: Int)
}
