package example.com.newgameapp.Fav

import androidx.lifecycle.LiveData
import androidx.room.*
import example.com.newgameapp.Models.Fav.FavModel

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: FavModel)

    @Update
    fun update(fav: FavModel)

    @Delete
    fun delete(fav: FavModel)

    @Query("delete from fav_table")
    fun deleteAllGames()


    @Query("select * from fav_table order by game_id desc")
    fun getAllGames(): LiveData<List<FavModel>>
}