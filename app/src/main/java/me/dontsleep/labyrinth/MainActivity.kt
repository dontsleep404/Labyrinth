package me.dontsleep.labyrinth

import DatabaseHelper
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import me.dontsleep.labyrinth.game.GameActivity
import me.dontsleep.labyrinth.utils.MyTimer
import me.dontsleep.labyrinth.utils.Timer

class MainActivity : AppCompatActivity() {
    lateinit var myCanvas: Canvas

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        GameActivity.database = databaseHelper.writableDatabase
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.apply {
            systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

        myCanvas = Canvas(this);
        myCanvas.invalidate()
        setContentView(myCanvas)
        var thread = Thread{
            MyTimer.init()
            var fpsLimit = Timer();
            while(true){
                MyTimer.update()
                if(fpsLimit.isUpdate(16, true)){
                    myCanvas.update()
                }
            }
        }.start()
    }
    override fun onDestroy() {
        GameActivity.database?.close()
        super.onDestroy()
    }
}
