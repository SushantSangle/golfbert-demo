package xyz.sushant.golfbertdemo.models

data class FlagDataPostions(var height: Float,var width: Float,var flagPostion: Float,var distanceCovered: Float,val whiteFlagPostion:Float,val blueFlagPostion:Float,){
    var anchor1_x: Float = 0.0f
    var anchor1_y: Float = 0.0f
    var anchor2_x: Float = 0.0f
    var anchor2_y: Float = 0.0f
    var x2: Float = 0.0f
    var y2: Float = 0.0f
    var color : String =""
    var strokeValue : Float =0f
}