package com.sundevs.comicbook.view.callback

import com.sundevs.comicbook.R

interface IProgressDialogContract {

    fun showProgressDialog(messageStringResource: Int = R.string.uploading_info)

    fun hideProgressDialog()
}
