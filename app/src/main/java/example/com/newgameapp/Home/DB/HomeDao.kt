package example.com.newgameapp.Home.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import example.com.newgameapp.Models.Game.Game


@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(home: Game)

    @Update
    fun update(home: Game)

    @Delete
    fun delete(home: Game)

    @Query("delete from game")
    fun deleteAllGames()

    @Query("SELECT * FROM Game WHERE name LIKE '%' || (:query) || '%'")
    fun getQueryGame(query: String): LiveData<List<Game>>

    @Query("SELECT COUNT(game_id) FROM Game")
    fun getAllGamesCount():Int

    @Query("select * from game order by game_id asc")
    fun getAllGames(): LiveData<List<Game>>
}