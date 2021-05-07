package example.com.newgameapp.Fav.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import example.com.newgameapp.Fav.DB.FavRepository
import example.com.newgameapp.Models.Fav.FavModel

class FavViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = FavRepository(app)
    private val getallGames = repository.getAllGames()

    fun insert(game: FavModel) {
        repository.insert(game)
    }


    fun delete(game: FavModel) {
        repository.delete(game)
    }

    fun getAllGames(): LiveData<List<FavModel>> {
        return getallGames
    }


}