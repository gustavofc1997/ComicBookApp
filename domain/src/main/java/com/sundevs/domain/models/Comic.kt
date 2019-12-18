package com.sundevs.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic (val name:String, val image:String?, val dateAdded:String?) : Parcelable