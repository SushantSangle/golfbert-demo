package xyz.sushant.golfbertdemo.models.course

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import xyz.sushant.golfbertdemo.models.Coordinates

@Parcelize
data class Course(
    var address: Address? = null,
    var coordinates: Coordinates? = null,
    var id: Int? = null,
    var name: String? = null,
    var phonenumber: String? = null,
) : Parcelable