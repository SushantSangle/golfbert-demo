package xyz.sushant.golfbertdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
    var lat: Double = 0.0,
    var long: Double = 0.0,
) : Parcelable