package mypromotion.vboo.com.snapchallenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_ranking.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.dataSource.RankingListDataSourceImpl
import mypromotion.vboo.com.snapchallenge.adapter.RankingAdapter

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set up the list view adapter
        val listUser = arrayListOf<User>()
        listUser.clear()
        listUser.add(RankingActivity.User("Fabien Bouvet", 1067, "https://randomuser.me/api/portraits/men/10.jpg"))
        listUser.add(RankingActivity.User("Francis Gros", 980, "https://randomuser.me/api/portraits/men/15.jpg"))
        listUser.add(RankingActivity.User("Nicolas Fornerod", 654, "https://randomuser.me/api/portraits/men/25.jpg"))
        listUser.add(RankingActivity.User("Julien Martin", 543, "https://randomuser.me/api/portraits/thumb/women/35.jpg"))
        listUser.add(RankingActivity.User("Julie Dupond", 398, "https://randomuser.me/api/portraits/thumb/women/45.jpg"))
        listUser.add(RankingActivity.User("Julien Fornerod", 298, "https://randomuser.me/api/portraits/thumb/men/11.jpg"))
        listUser.add(RankingActivity.User("Bruno Festin", 238, "https://randomuser.me/api/portraits/thumb/men/12.jpg"))
        listUser.add(RankingActivity.User("Nathalie Bouvet", 198, "https://randomuser.me/api/portraits/thumb/women/22.jpg"))
        listUser.add(RankingActivity.User("Emmanuel Voisin", 54, "https://randomuser.me/api/portraits/thumb/men/43.jpg"))
        listUser.add(RankingActivity.User("Pierre Machin", 52, "https://randomuser.me/api/portraits/thumb/men/11.jpg"))
        listUser.add(RankingActivity.User("Nora Bouret", 34, "https://randomuser.me/api/portraits/thumb/women/15.jpg"))
        listUser.add(RankingActivity.User("Romain Thami", 23, "https://randomuser.me/api/portraits/thumb/men/43.jpg"))
        listUser.add(RankingActivity.User("Emmanuel Voisin", 19, "https://randomuser.me/api/portraits/thumb/men/43.jpg"))
        listUser.add(RankingActivity.User("Fabrice Luchini", 15, "https://randomuser.me/api/portraits/thumb/men/42.jpg"))
        listUser.add(RankingActivity.User("Booba Voisin", 13, "https://randomuser.me/api/portraits/thumb/men/23.jpg"))
        listUser.add(RankingActivity.User("Benjamin Biolay", 12, "https://randomuser.me/api/portraits/thumb/men/24.jpg"))
        val viewAdapter = RankingAdapter(this)
        viewAdapter.listRankingDataSource = RankingListDataSourceImpl(listUser)

        // apply the manager and the adapter to the permit_list
        activity_ranking_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }

    class User(var name: String, var point: Int, var urlPic: String, var rank: Int? = 0)

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
