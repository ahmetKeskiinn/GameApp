package example.com.newgameapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.R

class PageAdapter(private val context: Context, val words : List<Game>): RecyclerView.Adapter<PageAdapter.PageHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder  =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.fragment_main, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        if( position == 0){
            if (words != null) {
                holder.nameTw.text = words.get(0).name
                holder.ratingTw.text = words.get(0).rating
                holder.releasedTw.text = words.get(0).released
                DownloadImageFromInternet(holder.imageView).execute(words.get(0).game_image)
                words.get(position)
            }
        }
        if(position == 1 ){
            if (words != null) {
                holder.nameTw.text = words.get(1).name
                holder.ratingTw.text = words.get(1).rating
                holder.releasedTw.text = words.get(1).released
                DownloadImageFromInternet(holder.imageView).execute(words.get(1).game_image)
            }
        }
        if(position == 2){
            if (words != null) {
                holder.nameTw.text = words.get(2).name
                holder.ratingTw.text = words.get(2).rating
                holder.releasedTw.text = words.get(2).released
                DownloadImageFromInternet(holder.imageView).execute(words.get(2).game_image)
            }
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

    override fun getItemCount(): Int = words.count()

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById<ImageView>(R.id.videoGameImage)
        val nameTw : TextView = view.findViewById(R.id.nameInLarge)
        val releasedTw : TextView = view.findViewById(R.id.releasedTwInLarge)
        val ratingTw : TextView = view.findViewById(R.id.ratingTw)

    }
}
