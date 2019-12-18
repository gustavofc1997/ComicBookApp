package com.sundevs.comicbook.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import com.sundevs.comicbook.R
import java.io.File

fun ImageView.loadImageFromUrl(
    urlString: String?,
    errorImageResource: Int = R.drawable.ic_launcher_foreground
) {
    if (urlString.isNullOrEmpty()) {
        Picasso.get()
            .load(errorImageResource)
            .placeholder(getProgressBarIndeterminate(context))
            .error(errorImageResource)
            .into(this)
    } else {
        val urlFile = File(urlString)
        if (urlFile.exists()) {
            this.loadImageFromFile(urlFile, errorImageResource)
        } else {
            Picasso.get()
                .load(urlString)
                .placeholder(getProgressBarIndeterminate(context))
                .error(errorImageResource)
                .into(this)
        }
    }
}

fun ImageView.loadImageFromFile(
    file: File?,
    errorImageResource: Int = R.drawable.ic_launcher_foreground
) {
    if (file?.exists() == true) {
        Picasso.get()
            .load(file)
            .placeholder(getProgressBarIndeterminate(context))
            .error(errorImageResource)
            .into(this)
    } else {
        Picasso.get()
            .load(errorImageResource)
            .placeholder(getProgressBarIndeterminate(context))
            .error(errorImageResource)
            .into(this)
    }
}


fun getProgressBarIndeterminate(context: Context): Drawable {
    return ProgressBar(context)
        .indeterminateDrawable
}