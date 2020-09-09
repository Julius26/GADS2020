package com.vesencom.gadsphaseii.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skill (
    val name: String,
    val score: Int,
    val country: String,
    val badgeUrl: String
): Parcelable