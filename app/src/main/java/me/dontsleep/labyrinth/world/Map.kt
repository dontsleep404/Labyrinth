package me.dontsleep.labyrinth.world

import kotlin.random.Random

class Maze(private val n: Int, private val x1: Int, private val y1: Int, private val x2: Int, private val y2: Int, private val seed: Int = Random.nextInt()) {
    private val visited = Array(n) { BooleanArray(n) }
    private val map = Array(2*n+1) { IntArray(2*n+1) }
    private val dx = intArrayOf(1, 0, -1, 0)
    private val dy = intArrayOf(0, 1, 0, -1)
    private val random = Random(seed)
    init {
        generateMaze()
        findPath(x1, y1, x2, y2)
    }
    public fun getMap(): Array<IntArray> {
        return map
    }
    private fun generateMaze() {
        for (i in 0 until n) {
            for (j in 0 until n) {
                visited[i][j] = false
            }
        }
        visited[x1][y1] = true
        for (i in 0 until 2*n+1) {
            for (j in 0 until 2*n+1) {
                map[i][j] = 0
            }
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                map[2*i+1][2*j+1] = 1
            }
        }
    }
    private fun randDirection(): IntArray {
        val directions = intArrayOf(0, 1, 2, 3)
        for (i in 0 until 4) {
            val j = random.nextInt(4)
            val tmp = directions[i]
            directions[i] = directions[j]
            directions[j] = tmp
        }
        return directions
    }
    private fun findPath(x: Int, y: Int, destX: Int, destY: Int) {
        if (x == destX && y == destY) {
            return
        }
        val directions = randDirection()
        for (i in 0 until 4) {
            val nx = x + dx[directions[i]]
            val ny = y + dy[directions[i]]
            if (nx in 0 until n && ny >= 0 && ny < n && !visited[nx][ny]) {
                map[2*x+1+dx[directions[i]]][2*y+1+dy[directions[i]]] = 1
                visited[nx][ny] = true
                findPath(nx, ny, destX, destY)
            }
        }

    }
}
class Map(){
    public fun genMap(n: Int, x1: Int, y1: Int, x2: Int, y2: Int, seed: Int = Random.nextInt()): Array<IntArray> {
        val maze = Maze(n, x1, y1, x2, y2, seed)
        return maze.getMap()
    }
}