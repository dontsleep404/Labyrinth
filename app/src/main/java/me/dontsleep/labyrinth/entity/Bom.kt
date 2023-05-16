import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import me.dontsleep.labyrinth.entity.Player

open class Bom(x: Float, y: Float, var dx: Float, var dy: Float, range: Float) : Item(x, y, range) {
    private var isActivated: Boolean = false
    fun isInRange(x: Float, y: Float): Boolean {
        return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) <= range * range
    }
    override fun onPlayerEnter(player: Player) {
        onActive(player)
    }
    open fun isDead(player: Player): Boolean {
        return isActivated || (((player.x - x) * (player.x - x) + (player.y - y) * (player.y - y)) > 1000000f)
    }
    open fun onActive(player: Player) {
        isActivated = true
    }
    override fun update(player: Player) {
        x += dx
        y += dy
        super.update(player)
    }
    override fun render(canvas: Canvas) {
        var paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        canvas.drawCircle(x, y, range, paint)
    }
}