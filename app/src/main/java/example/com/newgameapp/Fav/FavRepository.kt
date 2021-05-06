package example.com.newgameapp.Fav

import android.app.Application
import androidx.lifecycle.LiveData
import example.com.newgameapp.Models.Fav.FavModel
import example.com.newgameapp.Utils.subscribeOnBackground

class FavRepository(application: Application) {

    private var favDao: FavDao
    private var allNotes: LiveData<List<FavModel>>

    private val database = FavDataBase.getInstance(application)

    init {
        favDao = database.favDao()
        allNotes = favDao.getAllGames()
    }

    fun insert(fav: FavModel) {
//        Single.just(noteDao.insert(note))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()
        subscribeOnBackground {
            favDao.insert(fav)
        }
    }

    fun update(fav: FavModel) {
        subscribeOnBackground {
            favDao.update(fav)
        }
    }

    fun delete(fav: FavModel) {
        subscribeOnBackground {
            favDao.delete(fav)
        }
    }

    fun deleteAllGames() {
        subscribeOnBackground {
            favDao.deleteAllGames()
        }
    }

    fun getAllGames(): LiveData<List<FavModel>> {
        return allNotes
    }


}