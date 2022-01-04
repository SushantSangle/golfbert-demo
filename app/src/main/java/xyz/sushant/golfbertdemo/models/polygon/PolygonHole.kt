package xyz.sushant.golfbertdemo.models.polygon

import xyz.sushant.golfbertdemo.models.Coordinates

data class PolygonHole(
    var holeid: Int?,
    var polygon: ArrayList<Coordinates> = arrayListOf(),
    var surfacetype: String?
)