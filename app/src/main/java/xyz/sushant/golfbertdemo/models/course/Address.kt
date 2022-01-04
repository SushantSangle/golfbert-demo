package xyz.sushant.golfbertdemo.models.course

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    var city: String? = null,
    var country: String? = null,
    var state: String? = null,
    var street: String? = null,
    var zip: String? = null
) : Parcelable