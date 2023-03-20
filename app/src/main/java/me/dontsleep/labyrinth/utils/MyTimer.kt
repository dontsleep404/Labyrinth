package me.dontsleep.labyrinth.utils

import java.lang.Long.max
import java.util.Date

class MyTimer {
    companion object{
        var lastUpdate : Long = 0
        var lastDelta : Long = 1
        var deltaTimer : Long = 1
        fun init(){
            lastUpdate = Date().time
            lastDelta = lastUpdate
        }
        fun update(){
            lastUpdate = Date().time
        }
        fun updateDelta(){
            var now = Date().time
            deltaTimer = max(now - lastDelta, 1)
            lastDelta = now
        }
        fun getDelta() : Float = deltaTimer / 1000f
    }
}