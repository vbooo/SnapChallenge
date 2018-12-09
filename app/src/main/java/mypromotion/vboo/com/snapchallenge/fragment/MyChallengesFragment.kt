package mypromotion.vboo.com.snapchallenge.fragment


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_my_challenges.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.LayDownChallengeActivity
import mypromotion.vboo.com.snapchallenge.adapter.MyChallengeAdapter
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [MyChallengesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MyChallengesFragment : Fragment() {
    private lateinit var mainActivity: Context


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(my_toolbar)
        activity.title = resources.getString(R.string.my_challenges)

        // set up the list view adapter
        val listChallenge = arrayListOf<Challenge>()
        listChallenge.clear()
        listChallenge.add(Challenge("Aller à l'escalade", "", getDaysAgo(1), "https://randomuser.me/api/portraits/thumb/men/89.jpg", "https://randomuser.me/api/portraits/thumb/men/34.jpg"))
        listChallenge.add(Challenge("Prendre une douche tout habillé", "", getDaysAgo(2), "https://randomuser.me/api/portraits/thumb/women/33.jpg", "https://randomuser.me/api/portraits/thumb/men/34.jpg"))
        listChallenge.add(Challenge("Prendre le prof de maths au tableau", "", getDaysAgo(3), "https://randomuser.me/api/portraits/thumb/women/34.jpg", "https://randomuser.me/api/portraits/thumb/men/34.jpg"))
        listChallenge.add(Challenge("Faire la bise à une inconnue", "", getDaysAgo(4), "https://randomuser.me/api/portraits/thumb/men/34.jpg", "https://randomuser.me/api/portraits/thumb/men/84.jpg"))
        listChallenge.add(Challenge("Manger des haricots verts au Nutella avec du piment", "", getDaysAgo(5), "https://randomuser.me/api/portraits/thumb/men/34.jpg", "https://randomuser.me/api/portraits/thumb/men/83.jpg"))
        listChallenge.add(Challenge("Aller à la piscine", "", getDaysAgo(6), "https://randomuser.me/api/portraits/thumb/men/34.jpg", "https://randomuser.me/api/portraits/thumb/men/82.jpg"))
        listChallenge.add(Challenge("Ice bucket challenge", "", getDaysAgo(7), "https://randomuser.me/api/portraits/thumb/men/81.jpg", "https://randomuser.me/api/portraits/thumb/men/34.jpg"))
        listChallenge.add(Challenge("Aller à la Tour Eiffel", "", getDaysAgo(8), "https://randomuser.me/api/portraits/thumb/men/10.jpg", "https://randomuser.me/api/portraits/thumb/men/34.jpg"))
        val viewAdapter = MyChallengeAdapter(listChallenge, mainActivity)

        // apply the manager and the adapter to the permit_list
        fragment_my_challenge_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }

        fragment_my_challenge_fab.setOnClickListener {
            val intent = Intent(mainActivity, LayDownChallengeActivity::class.java)
            startActivity(intent)
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

    fun getDaysAgo(daysToAdd: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd)

        return calendar.time
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_my_challenges, menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    class Challenge(var action: String, var p: String, var time: Date, var author: String, var challengedUser: String)
}
