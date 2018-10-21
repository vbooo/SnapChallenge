package mypromotion.vboo.com.snapchallenge.fragment


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_my_challenges.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.PrivacyChallengeActivity
import mypromotion.vboo.com.snapchallenge.adapter.MyChallengeAdapter
import android.support.v7.widget.RecyclerView




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyChallengesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MyChallengesFragment : Fragment() {
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set up the list view adapter
        val listChallenge = arrayListOf<Challenge>()
        listChallenge.clear()
        listChallenge.add(Challenge("Aller à l'escalade", "", "00:12:34", "Francis -> moi"))
        listChallenge.add(Challenge("Prendre une douche tout habillé", "", "01:12:34", "Valérie -> moi"))
        listChallenge.add(Challenge("Prendre le prof de maths au tableau", "", "02:12:34", "Nathalie -> moi"))
        listChallenge.add(Challenge("Faire la bise à une inconnue", "", "04:34:12", "moi -> Pierre"))
        listChallenge.add(Challenge("Manger des haricots verts au Nutella", "", "05:12:34", "moi -> Marc"))
        listChallenge.add(Challenge("Aller à la piscine", "", "échoué", "moi -> Nicolas"))
        listChallenge.add(Challenge("Ice bucket challenge", "", "répondu", "Julien -> moi"))
        listChallenge.add(Challenge("Aller à la Tour Eiffel", "", "répondu", "Didier -> moi"))
        val viewAdapter = MyChallengeAdapter(listChallenge, mainActivity)

        // apply the manager and the adapter to the permit_list
        fragment_my_challenge_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }

        fragment_my_challenge_fab.setOnClickListener {
            val intentRanking = Intent(mainActivity, PrivacyChallengeActivity::class.java)
            startActivity(intentRanking)
        }

        fragment_my_challenge_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0)
                    fragment_my_challenge_fab.hide()
                else if (dy < 0)
                    fragment_my_challenge_fab.show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_challenges, container, false)
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

    class Challenge(var a: String, var p: String, var t: String, var actor: String)
}
