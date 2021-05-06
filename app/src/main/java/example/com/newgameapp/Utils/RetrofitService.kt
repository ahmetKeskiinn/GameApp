package example.com.newgameapp.Utils

import com.google.gson.GsonBuilder
import example.com.newgameapp.Models.Detail.ExampleDetail
import example.com.newgameapp.Models.Game.Example
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("games")
    fun getAllGames(@Query("key") key: String? ) : Call<Example>
    @Headers("Content-Type: application/json")
    @GET("games/{id}")
    fun getSelectedGame(@Path("id") id:String?, @Query("key") key: String? ) : Call<ExampleDetail>


    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rawg.io/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}