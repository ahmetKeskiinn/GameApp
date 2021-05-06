package example.com.newgameapp.Home

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import example.com.newgameapp.Models.Game.Game

@Database(entities = [Game::class], version = 1)
abstract class HomeDataBase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

    companion object {
        private var instance: HomeDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): HomeDataBase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, HomeDataBase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(
                    instance!!
                )
            }
        }

        private fun populateDatabase(db: HomeDataBase) {
            val homeDao = db.homeDao()

        }
    }



}