package xyz.sushant.golfbertdemo.models.course

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoursesList(
    var resources: ArrayList<Course> = arrayListOf()
) : Parcelable