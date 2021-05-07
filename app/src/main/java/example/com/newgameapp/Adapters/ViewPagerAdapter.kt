package example.com.newgameapp.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.ViewPagerFragment

class ViewPagerAdapter(private val myContext: Context?, fm: FragmentManager, internal var totalTabs: Int, private val list: List<Game>, private val count: Int) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return ViewPagerFragment(list.get(position).name ,list.get(position).game_id, list.get(position).game_image,list.get(position).rating , list.get(position).released)
    }


    override fun getCount(): Int {
        return totalTabs
    }
}