package com.sundevs.comicbook.view.dialog

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sundevs.comicbook.R
import com.sundevs.data.utils.EMPTY

class SimpleProgressDialog {

    class Builder(
        private val mActivity: AppCompatActivity,
        private val mProgressMessage: String?
    ) {
        fun create(): AlertDialog {
            val builder = AlertDialog.Builder(mActivity)
            val mDialogView: View? =
                    mActivity.layoutInflater.inflate(R.layout.layout_progress_dialog, null)
            val tvProgressMessage: TextView? = mDialogView?.findViewById(R.id.tvProgressMessage)
            tvProgressMessage?.text = mProgressMessage ?: EMPTY
            builder.setView(mDialogView)
            builder.setCancelable(false)
            return builder.create()
        }
    }
}
