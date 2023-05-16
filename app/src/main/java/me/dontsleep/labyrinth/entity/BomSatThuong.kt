import android.graphics.Color
import me.dontsleep.labyrinth.entity.Particle
import me.dontsleep.labyrinth.entity.Player
import me.dontsleep.labyrinth.game.GameActivity

class BomSatThuong(x: Float, y: Float, dx: Float, dy: Float, range: Float) : Bom(x, y, dx, dy, range) {
    override fun onActive(player: Player) {
        player.damage()
        for(i in 0..10){
            var dx = (Math.random() * 10 - 5).toFloat()
            var dy = (Math.random() * 10 - 5).toFloat()
            var size = 5f
            var life = 100f + (Math.random() * 1000).toFloat()
            var color = Color.RED
            GameActivity.particles.add(Particle(player.x, player.y, dx, dy, color, size, life))
        }
        super.onActive(player)
    }
}