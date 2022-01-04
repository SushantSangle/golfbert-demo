package xyz.sushant.golfbertdemo

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import xyz.sushant.golfbertdemo.models.Coordinates
import xyz.sushant.golfbertdemo.models.hole.Hole
import xyz.sushant.golfbertdemo.models.polygon.PolygonList
import xyz.sushant.golfbertdemo.network.DummyApi
import xyz.sushant.golfbertdemo.ui.theme.GolfbertDemoTheme
import xyz.sushant.golfbertdemo.utils.PointsTransformer
import xyz.sushant.golfbertdemo.views.GolfMap
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    val MAIN_ACTIVITY_TAG = "MAIN_ACTIVITY"
    val frameLayout by lazy { findViewById<FrameLayout>(R.id.parent) }
    val golfmap by lazy { findViewById<GolfMap>(R.id.golfMapMain) }
    private var width: Double = 0.0
    private var height: Double = 0.0
    private var polygonList = PolygonList()
    private var hole: Hole? = null

    fun initialize() {
        val vto1: ViewTreeObserver = frameLayout.getViewTreeObserver()
        vto1.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                width = frameLayout.getMeasuredWidth().toDouble()
                height = frameLayout.getMeasuredHeight().toDouble()
                val transformer = PointsTransformer(width = width, height = height, padding = 90.0)
                val normalizedValue = transformer.getTransformedPolygon(polygonList)
                Log.d(MAIN_ACTIVITY_TAG, Gson().toJson(normalizedValue))
                val coOrdinate = normalizedValue.resources[0].polygon[0]
                Log.e("0th Co-ordinate", "${coOrdinate.lat} ${coOrdinate.long}")
                golfmap.polygonList = normalizedValue
                hole?.let { golfmap.setHoleVectors(
                        it.vectors,
                        transformer,
                        Coordinates().apply {
                            lat = transformer.getTransformedLatitude(it.flagcoords.lat)
                            long = transformer.getTransformedLongitude(it.flagcoords.long)
                        }
                    )
                }
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        polygonList = DummyApi.getHolePolygons(1234).body() ?: PolygonList()
        hole = DummyApi.getHoleDetails(1234).body()
        initialize()
    }
}