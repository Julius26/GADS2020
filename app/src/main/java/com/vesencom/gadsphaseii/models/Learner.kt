package com.vesencom.gadsphaseii.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url

@Parcelize
data class Learner (
    val name: String,
    val hours: Int,
    val country: String,
    val badgeUrl: String
) : Parcelable