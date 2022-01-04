package xyz.sushant.golfbertdemo.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import xyz.sushant.golfbertdemo.R
import xyz.sushant.golfbertdemo.models.Coordinates
import xyz.sushant.golfbertdemo.models.FlagDataPostions
import xyz.sushant.golfbertdemo.models.hole.Hole
import xyz.sushant.golfbertdemo.models.hole.Vector
import xyz.sushant.golfbertdemo.models.polygon.PolygonList
import xyz.sushant.golfbertdemo.utils.PointsTransformer

class GolfMap(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var mResources: Resources = getResources()
    var paint = Paint()
    var polygonList: PolygonList = PolygonList()
        set(value) {
            field = value
        }
    var holeVector: ArrayList<Vector> = arrayListOf()
    var flagCoOrdinates: Coordinates? = null

    fun setHoleVectors(
        list: ArrayList<Vector>,
        transformer: PointsTransformer,
        flagCoOrdinates: Coordinates
    ) {
        holeVector = ArrayList(list.map {
            it.apply {
                lat = transformer.getTransformedLatitude(lat)
                long = transformer.getTransformedLongitude(long)
            }
        })
        this.flagCoOrdinates = flagCoOrdinates
        invalidate()
    }

    init {
        paint = Paint()
        paint.apply {
            this.color = Color.BLUE
            this.strokeWidth = 5f
            this.style = Paint.Style.FILL
            this.textSize = 50f
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        polygonList.resources.forEach {
            val size = it.polygon.size
            val path = Path()

            when (it.surfacetype) {
                "Green" -> {
                    paint.color = context.resources.getColor(R.color.green)
                }
                "Fairway" -> {
                    paint.color = context.resources.getColor(R.color.fairway)
                }
                "Sand" -> {
                    paint.color = context.resources.getColor(R.color.sand)
                }
                "Woods" -> {
                    paint.color = context.resources.getColor(R.color.woods)
                }

            }

            path.reset()
            path.moveTo(it.polygon[0].long.toFloat(), it.polygon[0].lat.toFloat())
            for (i in 1..size) {
                val point = it.polygon[(i + 1) % size]
                path.lineTo(point.long.toFloat(), point.lat.toFloat())
            }
            c.drawPath(path, paint)
        }

        holeVector.forEach {
            when (it.type) {
                "Blue" -> {
                    paint.color = Color.BLUE
                    c.drawText("Blue", it.long.toFloat() + 30f, it.lat.toFloat() + 25f, paint)
                    c.drawCircle(it.long.toFloat(), it.lat.toFloat(), 20f, paint)
                }
                "White" -> {
                    paint.color = Color.BLACK
                    c.drawText("White", it.long.toFloat() + 30f, it.lat.toFloat() + 25f, paint)
                    c.drawCircle(it.long.toFloat(), it.lat.toFloat(), 20f, paint)
                }
                "Red" -> {
                    paint.color = Color.RED
                    c.drawText("Red", it.long.toFloat() + 30f, it.lat.toFloat() + 25f, paint)
                    c.drawCircle(it.long.toFloat(), it.lat.toFloat(), 20f, paint)
                }
            }
        }

        flagCoOrdinates?.let {
            val x = it.long.toFloat()
            val y = it.lat.toFloat()
            val flagUnit = 20f

            paint.color = Color.RED

            paint.strokeWidth = 10f
            paint.style = Paint.Style.FILL_AND_STROKE


            c.drawPath(Path().apply {
                moveTo(x, y)
                rLineTo(0f,flagUnit)
                rLineTo(0f,-2 * flagUnit)
                rLineTo(flagUnit,flagUnit)
                rLineTo(-1 * flagUnit,0f)
            }, paint)
        }
    }
}