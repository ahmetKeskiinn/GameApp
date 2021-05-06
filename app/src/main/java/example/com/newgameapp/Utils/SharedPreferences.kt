package example.com.newgameapp.Utils

import android.content.Context
import android.content.SharedPreferences


class SharedPreferences{
    private val sharedPrefFile = "gameApp"
    private val gameCount:Int=0
    fun initShared(context:Context, total:Int){

        val pref =
            context!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putInt(gameCount.toString(),total)
        editor.apply()
    }
    fun getCount(context: Context): Int{
        val pref: SharedPreferences = context.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        return pref.getInt(gameCount.toString(), 0);
    }
}