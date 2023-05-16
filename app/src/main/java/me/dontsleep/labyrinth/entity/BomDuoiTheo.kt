import android.graphics.Canvas
import android.graphics.Color
import me.dontsleep.labyrinth.entity.Particle
import me.dontsleep.labyrinth.entity.Player
import me.dontsleep.labyrinth.game.GameActivity
import me.dontsleep.labyrinth.utils.MyTimer
import kotlin.math.*

class BomDuoiTheo(x: Float, y: Float, dx: Float, dy: Float, range: Float, private var lifeTime: Float) : Bom(x, y, dx, dy, range) {
    override fun onActive(player: Player) {
        player.damage()
        for(i in 0..10){
            var dx = (Math.random() * 10 - 5).toFloat()
            var dy = (Math.random() * 10 - 5).toFloat()
            var size = 5f
            var life = 100f + (Math.random() * 1000).toFloat()
            var color = Color.GREEN
            GameActivity.particles.add(Particle(player.x, player.y, dx, dy, color, size, life))
        }
        super.onActive(player)
    }
    override fun render(canvas: Canvas) {
        var paint = android.graphics.Paint()
        paint.color = Color.GREEN
        canvas.drawCircle(x, y, range, paint)
//        super.render(canvas)
    }
    override fun isDead(player: Player): Boolean {
        return super.isDead(player) || lifeTime <= 0f
    }
    override fun update(player: Player) {
        lifeTime -= MyTimer.getDelta() * 1000f
        GameActivity.particles.add(Particle(x, y, -dx + (Math.random()*4 - 2f).toFloat(), -dy + (Math.random()*4 - 2f).toFloat(),  Color.GREEN, 4f, 300f))
        var dx = player.x - x
        var dy = player.y - y
        var oldSpeed = sqrt(this.dx * this.dx + this.dy * this.dy)
        var oldRad = atan2(this.dy, this.dx)
        var rad = atan2(dy, dx)
        // check rad is left or right
        var left = false
        var right = false
        if (oldRad < 0f) oldRad += Math.PI.toFloat() * 2f
        if (rad < 0f) rad += Math.PI.toFloat() * 2f
        if (rad > oldRad) {
            if (rad - oldRad < Math.PI) left = true
            else right = true
        } else {
            if (oldRad - rad < Math.PI) right = true
            else left = true
        }
        if (left) oldRad += 0.03f
        if (right) oldRad -= 0.03f

        var newRad = oldRad
        this.dx = cos(newRad) * oldSpeed
        this.dy = sin(newRad) * oldSpeed
        

        super.update(player)
    }
}