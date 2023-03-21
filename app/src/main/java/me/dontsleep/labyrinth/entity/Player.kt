package me.dontsleep.labyrinth.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import kotlin.math.abs


class Player : Entity() {
    var color : Int = Color.GREEN;
    override fun update() {
        var dx = x - prex
        var dy = y - prey
        super.update()
    }
    override fun render(canvas: Canvas) {
        var paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas.drawRect(-width/2, -height/2, width/2,height/2,paint)
    }
}