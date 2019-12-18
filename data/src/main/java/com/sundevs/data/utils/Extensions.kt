package com.sundevs.data.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.convertDateFromApiToAppFormat(): String {
    val appFormat = SimpleDateFormat(APP_DATE_FORMAT, Locale.US)
    val serviceFormat = SimpleDateFormat(SERVICE_DATE_FORMAT, Locale.US)
    return appFormat.format(serviceFormat.parse(this))
}
