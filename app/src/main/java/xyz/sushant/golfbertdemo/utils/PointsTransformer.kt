package xyz.sushant.golfbertdemo.utils

import xyz.sushant.golfbertdemo.models.Coordinates
import xyz.sushant.golfbertdemo.models.polygon.PolygonHole
import xyz.sushant.golfbertdemo.models.polygon.PolygonList

class PointsTransformer(var width: Double, var height: Double, var padding: Double = 10.0) {
    fun getTransformedPolygon(polygons: PolygonList) : PolygonList {
        var minLat : Double = 90.0
        var maxLat : Double = -90.0
        var minLong : Double = 180.0
        var maxLong : Double = -180.0
        polygons.resources.forEach {
            it.polygon.forEach { point ->
                if(point.lat < minLat) {
                    minLat = point.lat
                }
                if(point.lat > maxLat) {
                    maxLat = point.lat
                }
                if(point.long < minLong) {
                    minLong = point.long
                }
                if(point.lat > maxLong) {
                    maxLong = point.long
                }
            }
        }

        val longDiff = maxLong - minLong
        val latDiff = maxLat - minLat

        val coOrdinateRatio = latDiff/longDiff
        val screenRatio = width/height

        /**
         * Variable to scale things to
         */
        val scale = if(coOrdinateRatio > screenRatio) { (width - 2 * padding) / latDiff } else { (height - 2 * padding) / longDiff }

        return PolygonList().apply {
            polygons.resources.forEach {
                resources.add(PolygonHole(
                    holeid = it.holeid,
                    polygon = ArrayList<Coordinates>().apply {
                        it.polygon.forEach {
                            add(Coordinates(
                                lat = ((it.lat - maxLat) * -1 * scale + padding),
                                long = ((it.long - minLong) * scale + padding)
                            ))
                        }
                    },
                    surfacetype = it.surfacetype
                ))
            }
        }
    }
}