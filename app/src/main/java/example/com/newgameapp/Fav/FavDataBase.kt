package example.com.newgameapp.Fav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import example.com.newgameapp.Models.Fav.FavModel

@Database(entities = [FavModel::class], version = 1)
abstract class FavDataBase : RoomDatabase() {

    abstract fun favDao(): FavDao

    companion object {
        private var instance: FavDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): FavDataBase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, FavDataBase::class.java,
                    "fav_database"
                )
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

        private fun populateDatabase(db: FavDataBase) {
            val favDao = db.favDao()


        }
    }


}