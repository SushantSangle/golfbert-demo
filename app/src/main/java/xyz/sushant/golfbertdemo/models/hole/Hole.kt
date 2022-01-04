package xyz.sushant.golfbertdemo.models.hole

import xyz.sushant.golfbertdemo.models.Coordinates

data class Hole(
    var courseid: Int,
    var dimensions: Dimensions,
    var flagcoords: Coordinates,
    var id: Int,
    var number: Int,
    var range: Range,
    var rotation: Double,
    var vectors: ArrayList<Vector>
)