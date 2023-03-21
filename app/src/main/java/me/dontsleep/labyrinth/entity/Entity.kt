package me.dontsleep.labyrinth.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.*

open class Entity {
    var x : Float = 0f
    var y : Float = 0f
    var width : Float = 1f
    var height : Float = 1f
    var prex : Float = 0f
    var prey : Float = 0f
    open fun update(){
        prex = x
        prey = y
    }
    open fun render(canvas: Canvas){
        var paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.color = Color.RED
        canvas.drawRect(-width/2, -height/2, width/2,height/2,paint)
        canvas.drawLine(0f, 0f,10f, 10f, paint);
    }
    fun distanceTo(other : Entity): Float{
        return sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y))
    }
    fun angleTo(other : Entity): Float{
        return atan2(other.y - y, other.x - x)
    }
    fun isCollision(other : Entity): Boolean{
        return (abs(centerX() - other.centerX()) <= ((width + other.width) / 2f)) && abs(centerY() - other.centerY()) <= ((height + other.height) / 2f)
    }
    private fun centerX() = x + width/2f;
    private fun centerY() = y + height/2f;
    fun bottomY() = y + height;
}