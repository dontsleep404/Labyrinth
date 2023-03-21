package me.dontsleep.labyrinth.world

import android.graphics.Canvas
import me.dontsleep.labyrinth.entity.Entity

class World {

    private var entityList : ArrayList<Entity> = arrayListOf()
    private var entityToAdd : ArrayList<Entity> = arrayListOf()
    private var entityToRemove : ArrayList<Entity> = arrayListOf()

    init {

    }

    fun update(){
        for(entity in entityToAdd)
            if(!entityList.contains(entity)) entityList.add(entity)
        entityToAdd.clear()

        for(entity in entityToRemove)
            if(entityList.contains(entity)) entityList.remove(entity)

        for(entity in entityList)
            entity.update()
        entityList.sortBy { it.bottomY() }
    }
    fun render(canvas: Canvas){
        for(entity in entityList){
            canvas.translate(entity.x, entity.y)
            entity.render(canvas)
            canvas.translate(-entity.x, -entity.y)
        }

    }
    fun getEntities() : ArrayList<Entity>{
        return entityList
    }
    fun addEntity(entity: Entity){
        entityToAdd.add(entity)
    }
    fun removeEntity(entity: Entity){
        entityToRemove.add(entity)
    }
}