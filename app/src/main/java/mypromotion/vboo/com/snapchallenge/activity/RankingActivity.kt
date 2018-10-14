package mypromotion.vboo.com.snapchallenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_ranking.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.RankingAdapter

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set up the list view adapter
        val listUser = arrayListOf<User>()
        listUser.clear()
        listUser.add(RankingActivity.User("1.Fabien Bouvet haha coucou jij heheh alo ??", "1067 points"))
        listUser.add(RankingActivity.User("2.Francis Gros", "980 points"))
        listUser.add(RankingActivity.User("3.Nicolas Fornerod", "654 points"))
        listUser.add(RankingActivity.User("4.Julien Martin", "543 points"))
        listUser.add(RankingActivity.User("5.Julie Dupond", "398 points"))
        listUser.add(RankingActivity.User("6.Julien Fornerod", "298 points"))
        listUser.add(RankingActivity.User("7.Bruno Festin", "238 points"))
        listUser.add(RankingActivity.User("8.Nathalie Bouvet", "198 points"))
        listUser.add(RankingActivity.User("9.Emmanuel Voisin", "54 points"))
        val viewAdapter = RankingAdapter(listUser, this)

        // apply the manager and the adapter to the permit_list
        activity_ranking_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }

    class User(var n: String, var p: String) {
         var name = n
         var points = p
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
