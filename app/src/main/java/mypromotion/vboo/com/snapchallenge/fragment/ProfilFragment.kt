package mypromotion.vboo.com.snapchallenge.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
        listChallengeUser.add(ChallengeAnswer(1, "https://randomuser.me/api/portraits/women/33.jpg", "Bisous en haut de la Tour Eiffel", "", null, Date(), 7, 32, 12,  1, "https://randomuser.me/api/portraits/thumb/women/33.jpg", 1))


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

    class UserProfil(var name: String, var point: Int, var urlPic: String, var rank: Int? = 0)

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
