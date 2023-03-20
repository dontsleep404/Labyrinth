package me.dontsleep.labyrinth.ui

import kotlin.math.atan2
import kotlin.math.sqrt

class Touch (var posX : Float, var posY : Float){
    fun getAngle(touch : Touch) : Float{
        return atan2(touch.posY - posY, touch.posX - posX)
    }
    fun getDist(touch: Touch) : Float{
        return sqrt((touch.posY - posY)*(touch.posY - posY) + (touch.posX - posX)*(touch.posX - posX))
    }
}