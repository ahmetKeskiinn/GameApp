package example.com.newgameapp.Fav.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.com.newgameapp.Adapters.FavAdapter
import example.com.newgameapp.Fav.ViewModel.FavViewModel
import example.com.newgameapp.Home.HomeAdapter
import example.com.newgameapp.R

class FavFragment : Fragment() {
    private lateinit var adapter: HomeAdapter
    private lateinit var vm: FavViewModel
    private lateinit var recyclerAdapter: FavAdapter
    private lateinit var favRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            favRecyclerView = it.findViewById(R.id.favRecyclerView)
            initRecycler(it)
        }


    }

    private fun initRecycler(fragmentActivity: FragmentActivity){
        favRecyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
        favRecyclerView.setHasFixedSize(true)

        recyclerAdapter = FavAdapter { clickedFav ->
        }
        favRecyclerView.adapter = recyclerAdapter

        initMV()
    }
    private fun initMV(){
        vm = ViewModelProviders.of(this)[FavViewModel::class.java]

        vm.getAllGames().observe(this, Observer {
                       recyclerAdapter.submitList(it)
        })
    }


    private fun initViewModel(){
    }
}