package example.com.newgameapp.Home.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import example.com.newgameapp.Home.DB.HomeRepository
import example.com.newgameapp.Models.Game.Example
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.Utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import example.com.newgameapp.Models.Game.Result

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = HomeRepository(app)
    private val allGames = repository.getAllGames()

    fun insert(game: Game) {
        repository.insert(game)
    }
    val gameList = MutableLiveData<List<Result?>>()
    val errorMessage = MutableLiveData<String>()

    fun hook(retrofitService: RetrofitService, key:String?) {

        val call: Call<Example> = retrofitService.getAllGames(key)
        lateinit var listGame: LiveData<List<Game>>
        var list:List<Result?> ?

        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                list = response.body()!!.getData()
                val listSize = list?.size
                for (i in 0..listSize!! - 1) {
                    val delim = ","
                    val item = list?.get(i).toString().split(delim)
                    insert(
                        Game(
                            item[2].toString(),
                            item[5],
                            item[3],
                            item[6].toString(),
                            item[1],
                            item[4],
                            item[0].toInt()
                        )
                    )

                    Log.d("TAG", "onResponse: "+ i)
                }
                gameList.postValue(list)
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    fun update(game: Game) {
        repository.update(game)
    }

    fun delete(game: Game) {
        repository.delete(game)
    }

    fun deleteAllGames() {
        repository.deleteAllGames()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return repository.getAllGames()
    }
    fun getAllGamesCount():Int{
        return repository.getAllGamesCount()
    }
    fun getQueryGame(name:String): LiveData<List<Game>> {
        return repository.getQueryGame(name)
    }


}