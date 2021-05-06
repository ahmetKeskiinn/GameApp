package example.com.newgameapp.Adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.newgameapp.R

class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var img_thumbnail: ImageView
    var txt_title: TextView

    init {
        img_thumbnail = itemView.findViewById(R.id.img_thumbail)
        txt_title = itemView.findViewById(R.id.txt_title)
    }
}