package com.sundevs.comicbook.view

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.sundevs.comicbook.view.callback.ErrorMessageListener
import com.sundevs.comicbook.view.callback.IProgressDialogContract
import com.sundevs.comicbook.view.dialog.ErrorDialog
import com.sundevs.comicbook.view.dialog.SimpleProgressDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.*
import java.math.BigDecimal.ZERO

open class BaseActivity : DaggerAppCompatActivity(), BaseView, ErrorMessageListener,
    IProgressDialogContract {

    private var progressDialog: AlertDialog? = null
    override fun showErrorDialog(error: String) {
        supportFragmentManager.beginTransaction()
            .add(ErrorDialog.newInstance(), ErrorDialog::class.java.name)
            .commitAllowingStateLoss()
    }

    override fun showErrorDialog(errorStringId: Int) {
        try {
            showErrorDialog(getString(errorStringId))
        } catch (e: Exception) {
            print(e)
        }
    }

    override fun showError(error: String) {
        window?.decorView?.findViewById<View>(android.R.id.content)
            ?.let {
                Snackbar.make(it, error, Snackbar.LENGTH_LONG)
                    .show()
            }
    }

    override fun showError(errorStringId: Int) {
        showError(getString(errorStringId))
    }

    override fun tryAgainAction() {}

    override fun onDismissClicked() {}


    override fun showProgressDialog(
        messageStringResource: Int
    ) {
        if (progressDialog == null) {
            progressDialog = SimpleProgressDialog
                .Builder(
                    this,
                    getString(messageStringResource)
                )
                .create()
        }
        progressDialog?.show()
    }

    override fun hideProgressDialog() {
        progressDialog?.hide()
    }
}
