package example.com.newgameapp.Home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import example.com.newgameapp.Adapters.PageAdapter
import example.com.newgameapp.Adapters.ViewPagerAdapter
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.R
import example.com.newgameapp.ShowDetails.ShowDetails
import example.com.newgameapp.Utils.RetrofitService
import example.com.newgameapp.Utils.SharedPreferences
import kotlin.math.log

class HomeFragment : Fragment() {
    private lateinit var tabLayout: TabLayout;
    private lateinit var pager : ViewPager
    private lateinit var vm: HomeViewModel
    private lateinit var recyclerAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: ImageButton
    private lateinit var searchVideoGames: EditText
    private lateinit var sorry: TextView
    private lateinit var pagerAdapter: PageAdapter

    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            tabLayout = it.findViewById(R.id.videoGamesTablayout)
            pager = it.findViewById(R.id.videoGamesViewPager)
            initialUI(it)
            initRecycler(it)
        }
    }
    private fun initialUI(fragmentActivity: FragmentActivity) {
        recyclerView = fragmentActivity.findViewById(R.id.videoGamesRecycler)
        searchButton = fragmentActivity.findViewById(R.id.searchButton)
        vm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        searchVideoGames = fragmentActivity.findViewById(R.id.searchEditText)
        sorry = fragmentActivity.findViewById(R.id.sorry)
        searchVideoGames.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(searchVideoGames.text.length>=3){
                    recyclerView.isVisible = true
                    tabLayout.visibility = View.GONE
                    pager.visibility = View.GONE
                    initialRecyclerViewForSearch()
                    Log.d("TAG", "afterTextChanged: "+ searchVideoGames.text.toString())
                }
                else{
                    recyclerView.isVisible = true
                    sorry.visibility = View.GONE
                    tabLayout.isVisible = true
                    pager.isVisible = true
                    initialRecyclerViewForData()
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "onTextChanged: "+searchVideoGames.text.toString())
            }
        })

    }

    private fun initialViewPager(fragmentActivity: FragmentActivity , fragmentList: List<Game>) {
        tabLayout = fragmentActivity.findViewById<TabLayout>(R.id.videoGamesTablayout)
        viewPager = fragmentActivity.findViewById<ViewPager>(R.id.videoGamesViewPager)



        val adapter = ViewPagerAdapter(context,fragmentActivity.supportFragmentManager,tabLayout!!.tabCount,fragmentList,fragmentList.size)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun initRecycler(fragmentActivity: FragmentActivity) {
        recyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
        recyclerView.setHasFixedSize(true)

        recyclerAdapter = HomeAdapter { clickedNote ->
            val intent = Intent(fragmentActivity, ShowDetails::class.java)
            intent.putExtra("id", clickedNote.game_id.toString())
            intent.putExtra("image", clickedNote.game_image.toString())

            startActivity(intent)
            searchVideoGames.setText("")

        }
        recyclerView.adapter = recyclerAdapter

        initMV()
    }
    private fun initMV() {
        val key = "185dba4e62b64f699699201d01021097"
        val count : Int = SharedPreferences().getCount(context!!)
        Log.d("TAG", "initMV: "+count)

        vm.hook(RetrofitService.getInstance(), key)
        vm.gameList.observe(this, Observer {
            kotlin.runCatching {
                initialRecyclerViewForData()
            }

        })


    }
    private fun initialRecyclerViewForSearch(){
        vm.getQueryGame(searchVideoGames.text.toString()).observe(this, Observer {
            if(it.size!=0){
                for(i in 0 .. it.size-1){
                    Log.d("TAG", "yy: "+it.get(i).game_id)
                    sorry.isVisible = false
                    recyclerAdapter.submitList(it)
                }
            }
            else{
                recyclerView.isVisible = false
                sorry.isVisible = true
            }
        })
    }
    private fun initialRecyclerViewForData(){
        vm.getAllGames().observe(this, Observer {
            var list: List<Game?> = it
            val fragmentList = mutableListOf<Game>()
            Log.d("TAG", "initMV: " + it.size)
            for (i in 0..3) {
                if (i == 0) {
                    list = list.drop(i)
                    fragmentList.add(it.get(i))
                    Log.d("TAG", "initMV: " + it.get(i))
                } else if (i == 1) {
                    list = list.drop(i)
                    fragmentList.add(it.get(i))
                    Log.d("TAG", "initMV: " + it.get(i))
                } else if (i == 2) {
                    list = list.drop(i)
                    fragmentList.add(it.get(i))
                    Log.d("TAG", "initMV: " + it.get(i))

                }

            }
            recyclerAdapter.submitList(list)
            activity?.let {

                initialViewPager(it , fragmentList)
            }


        })
    }
}