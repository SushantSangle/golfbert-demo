package xyz.sushant.golfbertdemo.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import xyz.sushant.golfbertdemo.R
import xyz.sushant.golfbertdemo.models.FlagDataPostions
import xyz.sushant.golfbertdemo.models.polygon.PolygonList

class GolfMap(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var mResources : Resources = getResources()
    var paint = Paint()
    var polygonList : PolygonList = PolygonList()
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint = Paint()
        paint.apply {
            this.color= Color.BLUE
            this.strokeWidth=5f
            this.style=Paint.Style.FILL_AND_STROKE
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        polygonList.resources.forEach {
            val size = it.polygon.size
            val path = Path()

            when(it.surfacetype) {
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
            path.moveTo(it.polygon[0].long.toFloat(),it.polygon[0].lat.toFloat())
            for(i in 1..size) {
                val point = it.polygon[(i + 1) % size]
                path.lineTo(point.long.toFloat(),point.lat.toFloat())
            }
            c.drawPath(path,paint)
        }
    }
}