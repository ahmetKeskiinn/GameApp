package example.com.newgameapp.Home

import android.app.Application
import androidx.lifecycle.LiveData
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.Utils.RetrofitService
import example.com.newgameapp.Utils.subscribeOnBackground

class HomeRepository(application: Application) {

    private var noteDao: HomeDao
    private var allNotes: LiveData<List<Game>>

    private val database = HomeDataBase.getInstance(application)

    init {
        noteDao = database.homeDao()
        allNotes = noteDao.getAllNotes()
    }
    fun getAllMovies(retrofitService : RetrofitService, key:String?) = retrofitService.getAllGames(key)

    fun insert(game: Game) {

        subscribeOnBackground {
            noteDao.insert(game)
        }
    }

    fun update(game: Game) {
        subscribeOnBackground {
            noteDao.update(game)
        }
    }

    fun delete(game: Game) {
        subscribeOnBackground {
            noteDao.delete(game)
        }
    }

    fun deleteAllNotes() {
        subscribeOnBackground {
            noteDao.deleteAllNotes()
        }
    }

    fun getAllGames(): LiveData<List<Game>> {
        return allNotes
    }
    fun getAllGamesCount():Int{
        return noteDao.getAllGamesCount()
    }
    fun getQueryGame(name:String): LiveData<List<Game>> {
        return noteDao.getQueryGame(name)
    }



}