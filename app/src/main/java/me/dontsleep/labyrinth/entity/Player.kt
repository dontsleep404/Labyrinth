package me.dontsleep.labyrinth.entity

import android.graphics.*
import me.dontsleep.labyrinth.utils.MyTimer
import kotlin.math.*


class Player : Entity() {
    var color : Int = Color.GREEN;
    var dr : Float = 1f;
    override fun update() {
        var dx = x - prex
        var dy = y - prey
        dr += 0.1f
        super.update()
    }
    override fun render(canvas: Canvas) {
        var paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas.drawRect(-width/2, -height/2, width/2,height/2,paint)
        paint.color = Color.RED
    }
}