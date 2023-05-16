package me.dontsleep.labyrinth.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import me.dontsleep.labyrinth.utils.MyTimer

class Particle (var x: Float, var y: Float, val dx: Float, val dy: Float, val color: Int, private val size: Float, private val life: Float) {
    var time = 0f
    fun update() {
        time += MyTimer.getDelta() * 1000f
        x += dx
        y += dy
    }
    fun isDead(): Boolean {
        return time >= life
    }
    fun render(canvas: Canvas) {
        var paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas.drawCircle(x, y, size, paint)
    }
}