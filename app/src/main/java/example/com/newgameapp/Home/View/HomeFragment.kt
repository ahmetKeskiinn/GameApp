package example.com.newgameapp.Home.View

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import example.com.newgameapp.Adapters.ViewPagerAdapter
import example.com.newgameapp.Home.HomeAdapter
import example.com.newgameapp.Home.ViewModel.HomeViewModel
import example.com.newgameapp.Models.Game.Game
import example.com.newgameapp.R
import example.com.newgameapp.ShowDetails.ShowDetails
import example.com.newgameapp.Utils.RetrofitService
import example.com.newgameapp.Utils.SharedPreferences


class HomeFragment : Fragment() {
    private lateinit var tabLayout: TabLayout;
    private lateinit var pager : ViewPager
    private lateinit var vm: HomeViewModel
    private lateinit var recyclerAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: ImageButton
    private lateinit var searchVideoGames: EditText
    private lateinit var sorry: TextView

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
            checkNetwork()
        }
    }
    private fun checkNetwork() {
        val connMgr = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        if(networkInfo !=null){
            }
        else{
            Toast.makeText(getActivity(),getString(R.string.turninternet),Toast.LENGTH_SHORT).show();
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
                if (searchVideoGames.text.length >= 3) {
                    recyclerView.isVisible = true
                    tabLayout.visibility = View.GONE
                    pager.visibility = View.GONE
                    initialRecyclerViewForSearch()
                } else {
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
            }
        })

    }

    private fun initialViewPager(fragmentActivity: FragmentActivity, fragmentList: List<Game>) {
        tabLayout = fragmentActivity.findViewById<TabLayout>(R.id.videoGamesTablayout)
        viewPager = fragmentActivity.findViewById<ViewPager>(R.id.videoGamesViewPager)



        val adapter = ViewPagerAdapter(
            context,
            fragmentActivity.supportFragmentManager,
            tabLayout!!.tabCount,
            fragmentList,
            fragmentList.size
        )
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

        recyclerAdapter = HomeAdapter { clickedGame ->
            val intent = Intent(fragmentActivity, ShowDetails::class.java)
            intent.putExtra("id", clickedGame.game_id.toString())
            intent.putExtra("image", clickedGame.game_image.toString())

            startActivity(intent)
            searchVideoGames.setText("")

        }
        recyclerView.adapter = recyclerAdapter

        initMV()
    }
    private fun initMV() {
        val key = "185dba4e62b64f699699201d01021097"
        val count : Int = SharedPreferences().getCount(context!!)


        vm.hook(RetrofitService.getInstance(), key)
        /*vm.gameList.observe(this, Observer {
            if (it.size == 20) {
                kotlin.runCatching {
                    initialRecyclerViewForData()
                }
            } else {
                Log.d("TAG", "initMV: "+"asdsaa")
            }

        })*/
        initialRecyclerViewForData()

    }
    private fun initialRecyclerViewForSearch(){
        vm.getQueryGame(searchVideoGames.text.toString()).observe(this, Observer {
            if (it.size != 0) {
                for (i in 0..it.size - 1) {
                    sorry.isVisible = false
                    recyclerAdapter.submitList(it)
                }
            } else {
                recyclerView.isVisible = false
                sorry.isVisible = true
            }
        })
    }
    private fun initialRecyclerViewForData(){
        vm.getAllGames().observe(this, Observer {
            if (it.size == 20) {
                var list: List<Game?> = it
                val fragmentList = mutableListOf<Game>()
                for (i in 0..3) {
                    if (i == 0) {
                        list = list.drop(i)
                        fragmentList.add(it.get(i))
                    } else if (i == 1) {
                        list = list.drop(i)
                        fragmentList.add(it.get(i))
                    } else if (i == 2) {
                        list = list.drop(i)
                        fragmentList.add(it.get(i))
                    }

                }
                recyclerAdapter.submitList(list)
                activity?.let {

                    initialViewPager(it, fragmentList)
                }
            }
        })
    }

}