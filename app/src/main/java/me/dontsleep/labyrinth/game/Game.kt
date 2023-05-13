package me.dontsleep.labyrinth.game

import android.database.sqlite.SQLiteDatabase
import android.graphics.Canvas
import me.dontsleep.labyrinth.ui.Panel

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
        var mapSize : Int = 5
        var mapSeed : Int = 3
        var map : Array<IntArray> = me.dontsleep.labyrinth.world.Map().genMap(mapSize, 0, 0, 2, 2, mapSeed)
        var paint = android.graphics.Paint()
    }
}