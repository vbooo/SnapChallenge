package mypromotion.vboo.com.snapchallenge.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.profil_fragment.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.ProfilAdapter
import mypromotion.vboo.com.snapchallenge.dataSource.ProfilDataSourceImpl
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.User
import java.util.*

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_fragment)

        val listChallengeUser = arrayListOf<ChallengeAnswer>()
        listChallengeUser.clear()
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/33.jpg", "Fab", "Bisous en haut de la Tour Eiffel", null, Date(), 7, 32, 32,  8, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/34.jpg", "Fab", "Ice Bucket challenge", null, Date(), 14, 132, 52,  2, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 2))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/50.jpg", "Fab", "En sous vêtement dans la neige", null, Date(), 10, 52, 62,  10, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/51.jpg", "Fab", "Faire du piano", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/53.jpg", "Fab", "Faire la plus grosse bombe à la piscine", null, Date(), 34, 72, 54,  0, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))


        val user = User(1, "Fabien Bouvet", Date(), "https://randomuser.me/api/portraits/men/33.jpg", 1500, 23, 140)
        val viewAdapter = ProfilAdapter(this, user)
        viewAdapter.profilDataSource = ProfilDataSourceImpl(listChallengeUser, user)

        // apply the manager and the adapter to the permit_list
        fragment_profil_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }
}
