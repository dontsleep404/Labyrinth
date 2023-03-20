package me.dontsleep.labyrinth.utils

class Timer(){
    private var lastUpdate : Long = MyTimer.lastUpdate;
    fun isUpdate(time : Long, reset : Boolean) : Boolean{
        if(time <= MyTimer.lastUpdate - lastUpdate){
            if(reset){
                lastUpdate += time
            }
            return true;
        }
        return false;
    }
}