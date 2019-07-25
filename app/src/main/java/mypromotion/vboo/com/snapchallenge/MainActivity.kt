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
import mypromotion.vboo.com.snapchallenge.model.network.AllUsersResult
import mypromotion.vboo.com.snapchallenge.model.network.FindUserByIdResult
import mypromotion.vboo.com.snapchallenge.network.ServiceResult
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener
import mypromotion.vboo.com.snapchallenge.network.service.UserService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //actionBar?.elevation = 2.0F
        //actionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeFragment(0)

       /* UserService().login(UserLoginData("toto", "totoPassword"), object : IServiceResultListener<LoginBodyResult> {

            override fun onResult(bodyResult: ServiceResult<LoginBodyResult>) {
                if (bodyResult.getmError() == null) {
                    bodyResult.mData?.result?.token?.let {
                        // save the token in Preferences
                    }
                    val gg = ""
                }
            }
        })

        UserService().add(AddUserData("Fabien", "fabien78", "", 18, "f@g.com", "passwordFab"), object : IServiceResultListener<LoginBodyResult> {

            override fun onResult(bodyResult: ServiceResult<LoginBodyResult>) {
                if (bodyResult.getmError() == null) {
                    bodyResult.mData?.result?.token?.let {
                        // save the token in Preferences
                    }
                    val gg = ""
                }
            }
        })*/

        UserService().getAllUsers("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVtYWlsQGNoYWxsZW5nZS5jb20iLCJfaWQiOiI1Y2ViMzE4MTYzNTdjMTRlZDFjYjg1OWUiLCJpYXQiOjE1NjQwNjQwMTEsImV4cCI6MTU2NDA2NzYxMX0.960s1AFuqfVfH-p9PWkX4eYW7UDAYRUDGNfbQ30aRTA", object : IServiceResultListener<AllUsersResult> {

            override fun onResult(bodyResult: ServiceResult<AllUsersResult>) {
                if (bodyResult.getmError() == null) {
                    bodyResult.mData
                    val gg = ""
                }
            }
        })

        UserService().findById("5ceb31126357c14ed1cb859d", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVtYWlsQGNoYWxsZW5nZS5jb20iLCJfaWQiOiI1Y2ViMzE4MTYzNTdjMTRlZDFjYjg1OWUiLCJpYXQiOjE1NjQwNjQwMTEsImV4cCI6MTU2NDA2NzYxMX0.960s1AFuqfVfH-p9PWkX4eYW7UDAYRUDGNfbQ30aRTA", object : IServiceResultListener<FindUserByIdResult> {

            override fun onResult(bodyResult: ServiceResult<FindUserByIdResult>) {
                if (bodyResult.getmError() == null) {
                    bodyResult.mData
                    val gg = ""
                }
            }
        })

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
