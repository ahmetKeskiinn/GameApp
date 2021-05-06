package example.com.newgameapp.Fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import example.com.newgameapp.Models.Fav.FavModel

class FavViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = FavRepository(app)
    private val allNotes = repository.getAllGames()

    fun insert(game: FavModel) {
        repository.insert(game)
    }


    fun delete(game: FavModel) {
        repository.delete(game)
    }

    fun getAllGames(): LiveData<List<FavModel>> {
        return allNotes
    }


}