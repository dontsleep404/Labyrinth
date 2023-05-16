import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "labyrinth.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create your database tables here
        val createTableQuery = "CREATE TABLE IF NOT EXISTS mytable (id INTEGER PRIMARY KEY, name TEXT, map INTEGER, time INTEGER)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade if needed
        // You can drop and recreate tables, or perform any other necessary migration tasks
    }
}
