package me.dontsleep.labyrinth.game

import android.graphics.Canvas
import me.dontsleep.labyrinth.ui.JoyStick
import me.dontsleep.labyrinth.ui.Panel

class GameScreen(x: Float, y: Float, width: Float, height: Float) : Panel(x, y, width, height) {
    var joyStick: JoyStick = JoyStick(x, y, width, height)
    init{
        addChild(joyStick)
    }
    override fun update() {
        var joy = joyStick.getAngleAndDist()
        joy?.let {
            var dist = joy[1]
            var angle = joy[0]
        }
        super.update()
    }

    override fun render(canvas: Canvas) {
        super.render(canvas)
    }
    override fun breakTouch(): Boolean {
        return false
    }
}