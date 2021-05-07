package example.com.newgameapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import example.com.newgameapp.Fav.View.FavFragment
import example.com.newgameapp.Home.View.HomeFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialUI()
    }
    private fun initialUI(){
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        val homeFragment = HomeFragment()
        val favFragment = FavFragment()
        makeCurrentFragment(homeFragment)
        val bottom_Nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_Nav.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home_dest -> makeCurrentFragment(homeFragment)
                R.id.fav_dest -> makeCurrentFragment(favFragment)
            }
            true

        }
    }
    private fun makeCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper,fragment)
                commit()
            }
}