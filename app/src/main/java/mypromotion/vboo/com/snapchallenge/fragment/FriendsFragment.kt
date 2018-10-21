package mypromotion.vboo.com.snapchallenge.fragment

import android.app.Activity
import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_my_challenges.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.FriendChallengesAdapter
import mypromotion.vboo.com.snapchallenge.adapter.MyChallengeAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FriendsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FriendsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mainActivity: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set up the list view adapter
        val listChallenge = arrayListOf<MyChallengesFragment.Challenge>()
        listChallenge.clear()
        listChallenge.add(MyChallengesFragment.Challenge("Aller à l'escalade", "", "00:12:34", "Francis -> Marc"))
        listChallenge.add(MyChallengesFragment.Challenge("Prendre une douche tout habillé", "", "01:12:34", "Valérie -> marie"))
        listChallenge.add(MyChallengesFragment.Challenge("Prendre le prof de maths au tableau", "", "02:12:34", "Nathalie -> Thomas"))
        listChallenge.add(MyChallengesFragment.Challenge("Faire la bise à une inconnue", "", "04:34:12", "Julie -> Pierre"))
        listChallenge.add(MyChallengesFragment.Challenge("Manger des haricots verts au Nutella", "", "05:12:34", "Romain -> Marc"))
        listChallenge.add(MyChallengesFragment.Challenge("Aller à la piscine", "", "échoué", "Bruno -> Nicolas"))
        listChallenge.add(MyChallengesFragment.Challenge("Ice bucket challenge", "", "répondu", "Julien -> David"))
        listChallenge.add(MyChallengesFragment.Challenge("Aller à la Tour Eiffel", "", "répondu", "Didier -> Théo"))

        val viewAdapter = FriendChallengesAdapter(listChallenge, mainActivity)

        // apply the manager and the adapter to the permit_list
        fragment_friends_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }

}
