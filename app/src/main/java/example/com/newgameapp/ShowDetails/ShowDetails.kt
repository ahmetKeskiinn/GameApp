package example.com.newgameapp.ShowDetails

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import example.com.newgameapp.Fav.FavViewModel
import example.com.newgameapp.Models.Detail.ExampleDetail
import example.com.newgameapp.Models.Fav.FavModel
import example.com.newgameapp.R
import example.com.newgameapp.Utils.RetrofitService
import kotlinx.android.synthetic.main.activity_show_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDetails : AppCompatActivity(),View.OnClickListener {
    private lateinit var imageView: ImageView;
    private lateinit var titleTw: TextView;
    private lateinit var releaseDateTw: TextView;
    private lateinit var metacriticTw: TextView
    private lateinit var descriptionTw: TextView
    private lateinit var likeButton: ImageButton;
    private lateinit var id:String
    private lateinit var url:String
    private lateinit var rating:String
    private lateinit var releaseDate : String
    private lateinit var metacritic : String
    private lateinit var description: String
    private lateinit var vm: FavViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        initialUI()
    }
    private fun initialUI() {
        imageView = findViewById(R.id.videoGameImage)
        titleTw = findViewById(R.id.nameTw)
        releaseDateTw = findViewById(R.id.releaseTw)
        metacriticTw = findViewById(R.id.metacriticTw)
        descriptionTw = findViewById(R.id.descriptionTw)
        likeButton = findViewById(R.id.likeButton)
        likeButton.setOnClickListener(this)
        id = intent.getStringExtra("id").toString()
        url = intent.getStringExtra("image").toString()
        Log.d("TAG", "initialUI: "+id)
        hook(RetrofitService.getInstance(),"185dba4e62b64f699699201d01021097",id)
    }
    fun hook(retrofitService: RetrofitService, key:String?, id : String) {
        val call: Call<ExampleDetail> = retrofitService.getSelectedGame(id,key)
        call.enqueue(object : Callback<ExampleDetail> {
            override fun onResponse(call: Call<ExampleDetail>, response: Response<ExampleDetail>) {
                Log.d("TAG", "onResponse: " +response.isSuccessful)
                Log.d("TAG", "onResponse: " + response.code())
                Log.d("TAG", "onResponse: "+response.body()!!.getData())
                val item: String? = response.body()!!.getData()
                val itemSplit : List<String>? = item?.split("?!")
                titleTw.text = itemSplit!!.get(0)
                releaseDateTw.text = itemSplit!!.get(1)
                metacriticTw.text = itemSplit!!.get(2)
                descriptionTw.text = itemSplit!!.get(3)
                rating = itemSplit!!.get(4)
            }

            override fun onFailure(call: Call<ExampleDetail>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message.toString()   )
            }
        })
        DownloadImageFromInternet(imageView).execute(url)
    }
    override fun onClick(v: View?) {
        if(v == likeButton){
            vm = ViewModelProviders.of(this)[FavViewModel::class.java]
            vm.insert(FavModel(nameTw.text.toString(),releaseDateTw.text.toString(),metacriticTw.text.toString(),rating,url,id))
            Toast.makeText(this, getString(R.string.addedFav), Toast.LENGTH_SHORT).show()

        }
    }
    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
            //Toast.makeText(applicationContext, "Please wait, it may take a few minute...",     Toast.LENGTH_SHORT).show()
            Log.d("TAG", ":+++++++ ")
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }
}