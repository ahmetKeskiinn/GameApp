package example.com.newgameapp.Home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.R

class HomeAdapter(private val onItemClickListener: (Game) -> Unit)
    : ListAdapter<Game, HomeAdapter.HomeHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.game_item, parent,
            false)
        return HomeHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        with(getItem(position)) {
            holder.nameTw.text = name
            holder.ratingTw.text  = rating

            holder.releasedTw.text = released
            DownloadImageFromInternet(holder.gameImageView).execute(game_image)
        }
    }
    fun getGameAt(position: Int) = getItem(position)


    inner class HomeHolder(iv: View) : RecyclerView.ViewHolder(iv) {

        val nameTw: TextView = itemView.findViewById(R.id.text_view_title)
        val ratingTw: TextView = itemView.findViewById(R.id.ratingTw)
        val releasedTw: TextView = itemView.findViewById(R.id.releasedTw)
        val gameImageView: ImageView = itemView.findViewById(R.id.gameImage)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    onItemClickListener(getItem(adapterPosition))
            }
        }
    }
    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
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


private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game) = oldItem.game_id == newItem.game_id

    override fun areContentsTheSame(oldItem: Game, newItem: Game) =
        oldItem.name == newItem.name
                && oldItem.methacritic == newItem.methacritic
                && oldItem.game_id == newItem.game_id && oldItem.game_pk == newItem.game_pk
                && oldItem.rating == newItem.rating

}