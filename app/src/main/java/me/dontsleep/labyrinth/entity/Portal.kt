package me.dontsleep.labyrinth.entity

import Item
import android.graphics.Canvas
import android.graphics.Color
import me.dontsleep.labyrinth.game.GameActivity
import kotlin.math.*

class Portal(x: Float, y: Float, range: Float, private val xOffset : Float, private val yOffset : Float) : Item(x, y, range) {
    override fun onPlayerEnter(player: Player) {
        player.x = xOffset.toFloat()
        player.y = yOffset.toFloat()
        GameActivity.nextLevel(player)
    }
    override fun update(player: Player) {
        for(i in 0..1){
            var rad = Math.random() * Math.PI * 2f
            var px = this.x + cos(rad) * range
            var py = this.y + sin(rad) * range
            var pdx = -cos(rad) * 2f
            var pdy = -sin(rad) * 2f
            GameActivity.particles.add(Particle(px.toFloat(), py.toFloat(), pdx.toFloat(), pdy.toFloat(), Color.BLUE, 5f, 1000f))
        }
        super.update(player)
    }
    override fun render(canvas: Canvas) {
//        var paint = android.graphics.Paint()
//        paint.color = android.graphics.Color.GREEN
//        canvas.drawCircle(x, y, range, paint)
        super.render(canvas)
    }
}
