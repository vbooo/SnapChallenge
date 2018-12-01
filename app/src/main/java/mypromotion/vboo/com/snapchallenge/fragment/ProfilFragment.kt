package mypromotion.vboo.com.snapchallenge.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_my_challenges.*
import kotlinx.android.synthetic.main.profil_fragment.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.ProfilAdapter
import mypromotion.vboo.com.snapchallenge.dataSource.ProfilDataSourceImpl
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.User
import java.util.*

class ProfilFragment : Fragment() {

    private lateinit var mainActivity: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.profil_fragment, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // set up the list view adapter
        val listChallengeUser = arrayListOf<ChallengeAnswer>()
        listChallengeUser.clear()
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/33.jpg", "Fab", "Bisous en haut de la Tour Eiffel", null, Date(), 7, 32, 32,  8, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/34.jpg", "Fab", "Ice Bucket challenge", null, Date(), 14, 132, 52,  2, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 2))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/50.jpg", "Fab", "En sous vêtement dans la neige", null, Date(), 10, 52, 62,  10, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/51.jpg", "Fab", "Faire du piano", null, Date(), 22, 62, 72,  7, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/men/53.jpg", "Fab", "Faire la plus grosse bombe à la piscine", null, Date(), 34, 72, 54,  0, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))


        val user = User(1, "Fabien Bouvet", Date(), "https://randomuser.me/api/portraits/men/33.jpg", 1500, 23, 140)
        val viewAdapter = ProfilAdapter(mainActivity, user)
        viewAdapter.profilDataSource = ProfilDataSourceImpl(listChallengeUser, user)

        // apply the manager and the adapter to the permit_list
        fragment_profil_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        instantiateContextOrActivity(context)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        instantiateContextOrActivity(activity)
    }

    /**
     * Create requestPermitActivity instance whatever the type is context or activity (for older android version)
     */
    private fun instantiateContextOrActivity(context: Context) {
        if (context is MainActivity) {
            mainActivity = context
        } else {
            throw RuntimeException(context.toString() + " is not of type RequestPermitActivity")
        }
    }
}
