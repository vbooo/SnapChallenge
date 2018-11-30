package mypromotion.vboo.com.snapchallenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_action_challengers.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.ActionChallengersAdapter
import mypromotion.vboo.com.snapchallenge.dataSource.ActionChallengersDataSourceImpl
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.User
import java.util.*

class ActionChallengersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_challengers)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listChallengeUser = arrayListOf<ChallengeAnswer>()
        listChallengeUser.clear()
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/33.jpg", "Fabien Bouvet", "Bisous en haut de la Tour Eiffel", null, Date(), 7, 32, 32,  8, "https://randomuser.me/api/portraits/thumb/men/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(2, "https://randomuser.me/api/portraits/women/34.jpg", "Valerie", "Embrasser mon chéri en haut de la Tour Eiffel", null, Date(), 14, 132, 52,  2, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 2))
        listChallengeUser.add(ChallengeAnswer(3, "https://randomuser.me/api/portraits/men/50.jpg", "Manon", "#TourEiffel #kiss", null, Date(), 10, 52, 62,  10, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(4, "https://randomuser.me/api/portraits/men/51.jpg", "Charline", "Bisous à la Tour Eiffel", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(5, "https://randomuser.me/api/portraits/men/52.jpg", "Kevin", "Bisous à la Tour Eiffel", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/men/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(6, "https://randomuser.me/api/portraits/men/53.jpg", "Bruno", "Bisous à la Tour Eiffel", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/men/31.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(7, "https://randomuser.me/api/portraits/men/54.jpg", "Nicolas", "Bisous à la Tour Eiffel", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/men/22.jpg", 1))


        val user = User(1, "Fabien Bouvet", Date(), "https://randomuser.me/api/portraits/men/33.jpg", 1500, 23, 140)
        val viewAdapter = ActionChallengersAdapter(this, user)
        viewAdapter.profilDataSource = ActionChallengersDataSourceImpl(listChallengeUser)

        // apply the manager and the adapter to the permit_list
        activity_action_challengers_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }

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
