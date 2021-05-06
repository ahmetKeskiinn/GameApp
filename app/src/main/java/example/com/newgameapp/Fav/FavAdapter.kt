package example.com.newgameapp.Fav

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
import example.com.newgameapp.Models.Fav.FavModel
import example.com.newgameapp.R


class FavAdapter(private val onItemClickListener: (FavModel) -> Unit)
    : ListAdapter<FavModel, FavAdapter.FavHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fav_row, parent,
            false)
        return FavHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        with(getItem(position)) {
            holder.name.text = name
            holder.released.text = released
            holder.rating.text = rating
            DownloadImageFromInternet(holder.image).execute(game_image)
        }
    }

    fun getFavAt(position: Int) = getItem(position)


    inner class FavHolder(iv: View) : RecyclerView.ViewHolder(iv) {

        val name: TextView = itemView.findViewById(R.id.text_view_titleFav)
        val released: TextView = itemView.findViewById(R.id.releasedTwFav)
        val rating : TextView = itemView.findViewById(R.id.ratingTwFav)
        val image : ImageView = itemView.findViewById(R.id.gameImageFav)
        //val tvPriority: TextView = itemView.findViewById(R.id.text_view_priority)

        init {
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION)
                    onItemClickListener(getItem(adapterPosition))
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
}

private val diffCallback = object : DiffUtil.ItemCallback<FavModel>() {
    override fun areItemsTheSame(oldItem: FavModel, newItem: FavModel) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: FavModel, newItem: FavModel) =
        oldItem.name == newItem.name

}