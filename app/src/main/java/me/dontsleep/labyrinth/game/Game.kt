package me.dontsleep.labyrinth.game

import android.database.sqlite.SQLiteDatabase
import android.graphics.Canvas
import me.dontsleep.labyrinth.entity.Player
import me.dontsleep.labyrinth.entity.Portal
import me.dontsleep.labyrinth.ui.Panel
import java.lang.Float.min

class Game(x: Float, y: Float, width: Float, height: Float) : Panel(x, y, width, height) {
    init {
        GameActivity.height = height
        GameActivity.width = width
        GameActivity.currentScreen = GameScreen(x, y, width, height)
        GameActivity.paint.color = android.graphics.Color.BLACK
        GameActivity.paint.textSize = 50f
    }
    override fun update() {
        GameActivity.currentScreen?.update()
        super.update()
    }

    override fun breakTouch(): Boolean {
        return false
    }

    override fun render(canvas: Canvas) {
        GameActivity.currentScreen?.render(canvas)
    }

    override fun onDrag(x: Float, y: Float) {
        GameActivity.currentScreen?.onDrag(x, y)
    }

    override fun onTouchDown(x: Float, y: Float) {
        GameActivity.currentScreen?.onTouchDown(x, y)
    }
    override fun onTouch() {
        GameActivity.currentScreen?.onTouch()
    }
    override fun onTouchUp(x: Float, y: Float) {
        GameActivity.currentScreen?.onTouchUp(x, y)
    }
}

class GameActivity{
    companion object{
        var currentScreen : Panel? = null
        var width : Float = 0f
        var height : Float = 0f
        var unitSize : Float = 200f
        var mapSize : Int = 4
        var mapSeed : Int = 3
        var map : Array<IntArray> = me.dontsleep.labyrinth.world.Map().startMap(mapSize, 0, 0, 2, 2, mapSeed)
        var paint = android.graphics.Paint()
        var particles = ArrayList<me.dontsleep.labyrinth.entity.Particle>()
        var portals = ArrayList<Portal>(
            arrayListOf(
                Portal((2f*mapSize-0.5f)*unitSize, (2f*mapSize-0.5f)*unitSize, unitSize/2f, unitSize * 1.5f, unitSize * 1.5f)
            )
        )
        var timeSpawnBom = 100f
        var playerHealth = 10000
        var level = 0
        fun nextLevel(player: Player){
            level++
            mapSize++
            mapSeed++
            map = me.dontsleep.labyrinth.world.Map().genMap(mapSize, 0, 0, mapSize-1, mapSize-1, mapSeed)
            playerHealth = 5
            player.maxHealth = playerHealth
            player.health = playerHealth
            timeSpawnBom = 1000f - min(level*200f, 900f)
            particles.clear()
            portals.clear()
            portals.add(Portal((2f*mapSize-0.5f)*unitSize, (2f*mapSize-0.5f)*unitSize, unitSize/2f, unitSize * 1.5f, unitSize * 1.5f))
        }
    }
}