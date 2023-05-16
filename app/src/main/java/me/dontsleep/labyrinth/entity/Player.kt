package me.dontsleep.labyrinth.entity

import android.graphics.*
import me.dontsleep.labyrinth.utils.MyTimer
import me.dontsleep.labyrinth.utils.Timer
import kotlin.math.*


class Player : Entity() {
    var color : Int = Color.GREEN;
    var timer : Timer = Timer()
    var speed : Float = 500f
    var health : Int = 3
    var maxHealth : Int = 3
    var stage = 0
    var deltaX = 0f
    var deltaY = 0f
    override fun update() {
        deltaX = prex - x
        deltaY = prey - y
        if(timer.isUpdate(1000, true)){
            stage++
            if(stage > 2) stage = 0
        }
        super.update()
    }
    fun damage(){
        health--
    }
    fun isDead() = health <= 0
    override fun render(canvas: Canvas) {
        val paint = Paint()
        val centerX: Float = 0f
        val centerY: Float = 0f
        val size: Float = width

        // Draw the player shape
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        canvas.drawRect(centerX - size / 2f, centerY - size / 2f, centerX + size / 2f, centerY + size / 2f, paint)

        // Draw the square outline
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = size / 20f
        canvas.drawRect(centerX - size / 2f, centerY - size / 2f, centerX + size / 2f, centerY + size / 2f, paint)

        // Calculate eye positions based on delta values
        // SQUARE EYE
        val eyeSize = size / 5f
        val eyeX = centerX - width/4 - deltaX / 2f
        val eyeY = centerY - deltaY / 2f - height / 6

        val eyeX2 = centerX + width/4 - deltaX / 2f
        val eyeY2 = centerY - deltaY / 2f - height / 6
        
        // Draw the eyes
        paint.color = Color.BLACK
        canvas.drawRect(eyeX - eyeSize / 2f, eyeY - eyeSize / 2f, eyeX + eyeSize / 2f, eyeY + eyeSize / 2f, paint)
        canvas.drawRect(eyeX2 - eyeSize / 2f, eyeY2 - eyeSize / 2f, eyeX2 + eyeSize / 2f, eyeY2 + eyeSize / 2f, paint)

        // draw mount
        val mountSize = size / 8f
        val mountX = centerX - deltaX / 2f
        val mountY = centerY + height / 6
        paint.color = Color.BLACK
        canvas.drawRect(mountX - mountSize, mountY - mountSize / 2f, mountX + mountSize, mountY + mountSize / 2f, paint)
        // draw bar on head
        val barSize = size / 2f
        val barX = centerX
        val barY = centerY - height
        paint.color = Color.BLACK
        canvas.drawRect(barX - barSize, barY - barSize / 4f, barX + barSize, barY + barSize / 4f, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas.drawRect(barX - barSize * health / maxHealth, barY - barSize / 4f, barX + barSize * health / maxHealth, barY + barSize / 4f, paint)
        // draw bar on foot
        
    }
}