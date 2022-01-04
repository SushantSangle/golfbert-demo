package xyz.sushant.golfbertdemo.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
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
        paint.apply { this.color= Color.BLUE
            this.strokeWidth=5f
            this.style=Paint.Style.STROKE
            this.setStrokeCap(Paint.Cap.ROUND);
            this.setStrokeJoin(Paint.Join.ROUND);}
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        polygonList.resources.forEach {
            val size = it.polygon.size

            for(i in it.polygon.indices) {
                val point1 = it.polygon[i]
                val point2 = it.polygon[(i+1) % size]
                c.drawLine(
                    point1.lat.toFloat(),
                    point1.long.toFloat(),
                    point2.lat.toFloat(),
                    point2.long.toFloat(),
                    paint
                )
            }
        }
    }
}