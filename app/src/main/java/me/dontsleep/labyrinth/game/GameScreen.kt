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
        player.y = 0f
        player.width = 50f
        player.height = 50f
        player.x = GameActivity.unitSize * 1.5f
        player.y = GameActivity.unitSize * 1.5f
        world.addEntity(player)
    }
    override fun update() {
        var joy = joyStick.getAngleAndDist()
        joy?.let {
            var dist = joy[1]
            var angle = joy[0]
            var dx = dist * player.speed * MyTimer.getDelta() * cos(angle)
            var dy = dist * player.speed * MyTimer.getDelta() * sin(angle)
            // check collision to map
            val map = GameActivity.map
            var top = (player.y + dy - player.height/2) / GameActivity.unitSize
            var bottom = (player.y + dy + player.height/2) / GameActivity.unitSize
            var left = (player.x + dx - player.width/2) / GameActivity.unitSize
            var right = (player.x + dx + player.width/2) / GameActivity.unitSize
            var x = (player.x) / GameActivity.unitSize
            var y = (player.y) / GameActivity.unitSize
            if(map[x.toInt()][top.toInt()] == 1 && map[x.toInt()][bottom.toInt()] == 1){
                player.y += dy
            }
            if(map[left.toInt()][y.toInt()] == 1 && map[right.toInt()][y.toInt()] == 1){
                player.x += dx
            }
        }
        world.update()
        super.update()
    }

    override fun render(canvas: Canvas) {
        canvas.translate(-player.x+width/2, -player.y+height/2)

        val map = GameActivity.map
        val shadowSize = 10f
        for(i in 0 until map.size){
            for(j in 0 until map[i].size){
                if(map[i][j] == 0){
                    GameActivity.paint.color = Color.argb(25, 0, 0, 0)
                    canvas.drawRect(i*GameActivity.unitSize + shadowSize, j*GameActivity.unitSize + shadowSize, (i+1)*GameActivity.unitSize + shadowSize, (j+1)*GameActivity.unitSize + shadowSize, GameActivity.paint)
                }
            }
        }
        world.render(canvas)
        for(i in 0 until map.size){
            for(j in 0 until map[i].size){
                if(map[i][j] == 0){
                    GameActivity.paint.color = Color.argb(255, 0, 0, 0)
                    canvas.drawRect(i*GameActivity.unitSize, j*GameActivity.unitSize, (i+1)*GameActivity.unitSize, (j+1)*GameActivity.unitSize, GameActivity.paint)
                }
            }
        }
        canvas.translate(player.x-width/2, player.y-height/2)
        super.render(canvas)
    }
    override fun breakTouch(): Boolean {
        return false
    }
}