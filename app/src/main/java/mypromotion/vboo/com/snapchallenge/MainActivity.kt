package mypromotion.vboo.com.snapchallenge

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import mypromotion.vboo.com.snapchallenge.activity.RankingActivity
import mypromotion.vboo.com.snapchallenge.fragment.HomeFragment
import mypromotion.vboo.com.snapchallenge.fragment.MyChallengesFragment
import mypromotion.vboo.com.snapchallenge.fragment.ProfilFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //actionBar?.elevation = 2.0F
        //actionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeFragment(0)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my_challenges -> {
                changeFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_avatar -> {
                changeFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(position: Int) {
        val newFragment = when (position) {
            1 -> MyChallengesFragment()
            2 -> ProfilFragment()
            else -> HomeFragment()
        }

        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment).commit()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> true
            R.id.action_ranking -> {
                val intentRanking = Intent(this, RankingActivity::class.java)
                startActivity(intentRanking)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
