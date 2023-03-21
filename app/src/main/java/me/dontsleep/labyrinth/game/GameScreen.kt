package me.dontsleep.labyrinth.game

import android.graphics.Canvas
import android.graphics.Color
import me.dontsleep.labyrinth.entity.Player
import me.dontsleep.labyrinth.ui.JoyStick
import me.dontsleep.labyrinth.ui.Panel
import me.dontsleep.labyrinth.utils.MyTimer
import me.dontsleep.labyrinth.world.World
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class GameScreen(x: Float, y: Float, width: Float, height: Float) : Panel(x, y, width, height) {
    var joyStick: JoyStick = JoyStick(x, y, width, height)
    var world : World = World()
    var random = Random(1)
    private var player = Player()
    init{
        addChild(joyStick)
        player.x = 100f
        player.y = 100f
        player.width = 100f
        player.height = 100f
        world.addEntity(player)
        for(i in 1..10){
            var p = Player();
            p.x = random.nextFloat() * 1000;
            p.y = random.nextFloat() * 1000;
            p.width = 100f;
            p.height = 100f;
            p.color = Color.RED
            world.addEntity(p)
        }
    }
    override fun update() {
        var joy = joyStick.getAngleAndDist()
        joy?.let {
            var dist = joy[1]
            var angle = joy[0]
            player.x += dist * 1000f * MyTimer.getDelta() * cos(angle)
            player.y += dist * 1000f * MyTimer.getDelta() * sin(angle)
        }
        world.update()
        super.update()
    }

    override fun render(canvas: Canvas) {
        world.render(canvas)
        super.render(canvas)
    }
    override fun breakTouch(): Boolean {
        return false
    }
}