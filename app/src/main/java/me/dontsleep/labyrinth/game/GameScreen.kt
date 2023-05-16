package me.dontsleep.labyrinth.game

import Bom
import BomDuoiTheo
import BomSatThuong
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import me.dontsleep.labyrinth.entity.Player
import me.dontsleep.labyrinth.ui.JoyStick
import me.dontsleep.labyrinth.ui.Panel
import me.dontsleep.labyrinth.utils.MyTimer
import me.dontsleep.labyrinth.utils.Timer
import me.dontsleep.labyrinth.world.World
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class GameScreen(x: Float, y: Float, width: Float, height: Float) : Panel(x, y, width, height) {
    var joyStick: JoyStick = JoyStick(x, y, width, height)
    var world : World = World()
    var random = Random(1)
    private var player = Player()
    var bomTimer = Timer()
    var boms = ArrayList<Bom>()
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
        if(bomTimer.isUpdate(GameActivity.timeSpawnBom.toLong(), true)){
            var rad = random.nextInt(0, 360) / 180f * Math.PI.toFloat()
            var x = player.x + cos(rad) * 1000
            var y = player.y + sin(rad) * 1000
            var dx = -cos(rad) * 10
            var dy = -sin(rad) * 10
            var size = 10f + random.nextFloat() * 10
            var rand = random.nextInt(0, 2)
            if(rand == 0) boms.add(BomDuoiTheo(x, y, dx/2, dy/2, size, 10000f))
            else if(rand == 1) boms.add(BomSatThuong(x, y, dx, dy, size))
            else boms.add(BomSatThuong(x, y, dx, dy, size))
        }
        if(player.isDead()) {
            player.x = GameActivity.unitSize * 1.5f
            player.y = GameActivity.unitSize * 1.5f
            player.health = GameActivity.playerHealth
            player.maxHealth = GameActivity.playerHealth
            boms.clear()
        }
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
        for(bom in boms){
            bom.update(player);
        }
        for(particle in GameActivity.particles){
            particle.update()
        }
        for(portal in GameActivity.portals){
            portal.update(player)
        }
        boms.removeAll { it.isDead(player) }
        GameActivity.particles.removeAll { it.isDead() }
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
        for(particle in GameActivity.particles){
            particle.render(canvas)
        }
        for(bom in boms){
            bom.render(canvas)
        }
        for(portal in GameActivity.portals){
            portal.render(canvas)
        }
        if(GameActivity.level == 0){
            GameActivity.paint.color = Color.parseColor("#000000")
            GameActivity.paint.textSize = 100f
            GameActivity.paint.textAlign = Paint.Align.CENTER
            GameActivity.paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            var text = "Labyrinth"
            var centerX = (GameActivity.unitSize) * (GameActivity.mapSize + 0.5f)
            var centerY = height/2 - 100f
            canvas.drawText(text, centerX, centerY, GameActivity.paint)
        }
        canvas.translate(player.x-width/2, player.y-height/2)
        if(GameActivity.level != 0){
            GameActivity.paint.textSize = 30f
            GameActivity.paint.textAlign = Paint.Align.CENTER
            GameActivity.paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            var text = "Level ${GameActivity.level}"
            var recW = 200f
            var recH = 100f
            GameActivity.paint.color = Color.parseColor("#000000")
            GameActivity.paint.style = Paint.Style.FILL
            canvas.drawRect(0f, 0f, recW, recH, GameActivity.paint)
            GameActivity.paint.color = Color.parseColor("#FFFFFF")
            canvas.drawText(text, recW/2, recH/2, GameActivity.paint)
        }
        super.render(canvas)
    }
    override fun breakTouch(): Boolean {
        return false
    }
}