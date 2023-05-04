package me.dontsleep.labyrinth

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import me.dontsleep.labyrinth.game.Game
import me.dontsleep.labyrinth.utils.MyTimer

class Canvas(context: Context?) : View(context){
    var game : Game? = null
    lateinit var gameCanvas: Canvas
    init{
    }
    override fun onDraw(canvas: Canvas?) {
        if(game == null) game = Game(0f, 0f, width.toFloat(), height.toFloat())
        game?.let{
            MyTimer.updateDelta()
            game?.update()
        }
        canvas?.let {
            game?.render(canvas)
        }
    }
    fun update(){
        invalidate()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event!!.let {
            when(event.action){
                MotionEvent.ACTION_DOWN -> game?.onTouchDown(event.x, event.y)
                MotionEvent.ACTION_UP -> game?.onTouchUp(event.x, event.y)
                MotionEvent.ACTION_MOVE -> game?.onDrag(event.x, event.y)
                else -> {}
            }
        }
        return true
    }
}