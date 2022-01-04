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

class GolfMap(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var mResources : Resources = getResources()
    var paint = Paint()

    init {
        paint = Paint()
        paint?.apply { this.color= Color.BLUE
            this.strokeWidth=5f
            this.style=Paint.Style.STROKE
            this.setStrokeCap(Paint.Cap.ROUND);
            this.setStrokeJoin(Paint.Join.ROUND);}
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
    }
}