package example.com.newgameapp.Home.DB

import android.app.Application
import androidx.lifecycle.LiveData
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.Utils.RetrofitService
import example.com.newgameapp.Utils.subscribeOnBackground

class HomeRepository(application: Application) {

    private var homeDao: HomeDao
    private var allGames: LiveData<List<Game>>

    private val database = HomeDataBase.getInstance(application)

    init {
        homeDao = database.homeDao()
        allGames = homeDao.getAllGames()
    }
    fun getAllMovies(retrofitService : RetrofitService, key:String?) = retrofitService.getAllGames(key)

    fun insert(game: Game) {

        subscribeOnBackground {
            homeDao.insert(game)
        }
    }

    fun update(game: Game) {
        subscribeOnBackground {
            homeDao.update(game)
        }
    }

    fun delete(game: Game) {
        subscribeOnBackground {
            homeDao.delete(game)
        }
    }

    fun deleteAllGames() {
        subscribeOnBackground {
            homeDao.deleteAllGames()
        }
    }

    fun getAllGames(): LiveData<List<Game>> {
        return allGames
    }
    fun getAllGamesCount():Int{
        return homeDao.getAllGamesCount()
    }
    fun getQueryGame(name:String): LiveData<List<Game>> {
        return homeDao.getQueryGame(name)
    }



}