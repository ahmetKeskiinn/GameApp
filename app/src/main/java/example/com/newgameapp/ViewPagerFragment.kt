package example.com.newgameapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import example.com.newgameapp.ShowDetails.ShowDetails


class ViewPagerFragment(private val name: String, private val gameId: Int, private val image: String, private val rating:String, private val released: String) : Fragment(), View.OnClickListener {
    private lateinit var gameImage: ImageView
    private lateinit var nameTw: TextView
    private lateinit var releaseTw : TextView
    private lateinit var ratingTw : TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_view_pager, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTw=view.findViewById(R.id.nameInLarge)
        nameTw.text = name
        releaseTw = view.findViewById(R.id.releasedTwInLarge)
        releaseTw.text = released
        ratingTw = view.findViewById(R.id.ratingTw)
        ratingTw.text = rating
        gameImage = view!!.findViewById(R.id.videoGameImage)

        gameImage.setOnClickListener(this)
        DownloadImageFromInternet(gameImage).execute(image)
    }

    override fun onClick(v: View?) {
        if(v == gameImage){
            val intent = Intent(context, ShowDetails::class.java)
            intent.putExtra("id",gameId.toString())
            intent.putExtra("image", image)
            startActivity(intent)
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
}// Required empty public constructor