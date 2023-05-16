import android.graphics.Canvas
import me.dontsleep.labyrinth.entity.Player

open class Item(var x: Float, var y: Float, val range: Float){
    private fun isInRange(x: Float, y: Float): Boolean {
        return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) <= range * range
    }
    open fun update(player: Player){
        if(isInRange(player.x, player.y)){
            onPlayerEnter(player)
        }
    }
    open fun onPlayerEnter(player: Player) {
    }
    open fun render(canvas: Canvas) {
    }
}