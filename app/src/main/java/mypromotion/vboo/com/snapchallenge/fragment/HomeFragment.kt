package mypromotion.vboo.com.snapchallenge.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.LinearLayout
import com.getbase.floatingactionbutton.FloatingActionsMenu
import kotlinx.android.synthetic.main.fab_list_challenge.*
import kotlinx.android.synthetic.main.fragment_home.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.LayDownChallengeActivity
import mypromotion.vboo.com.snapchallenge.activity.PublishChallengeActivity
import mypromotion.vboo.com.snapchallenge.activity.RankingActivity
import mypromotion.vboo.com.snapchallenge.adapter.HomeChallengeAdapter
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var mainActivity: Context
    private lateinit var activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(fragment_home_toolbar)
        activity.title = resources.getString(R.string.community)

        val listChallenge = arrayListOf<ChallengeAnswer>()
        listChallenge.clear()
        listChallenge.add(ChallengeAnswer(1, "https://youtu.be/tzzVwl9DDLM", "Fabien Bouvet", "Ice bucket challenge !", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/65.jpg", 2))
        listChallenge.add(ChallengeAnswer(2, "https://randomuser.me/api/portraits/women/33.jpg", "Francis Gros", "Bisous en haut de la Tour Eiffel", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/64.jpg", 1))
        listChallenge.add(ChallengeAnswer(3, "https://youtu.be/tzzVwl9DDLM", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/women/63.jpg", 2))
        listChallenge.add(ChallengeAnswer(4, "https://randomuser.me/api/portraits/men/14.jpg", "Julien Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/63.jpg", 1))
        listChallenge.add(ChallengeAnswer(5, "https://youtu.be/tzzVwl9DDLM", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/women/63.jpg", 2))
        listChallenge.add(ChallengeAnswer(6, "https://randomuser.me/api/portraits/men/13.jpg", "Valerie Dupond", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/women/63.jpg", 1))
        listChallenge.add(ChallengeAnswer(3, "https://youtu.be/tzzVwl9DDLM", "Laure martin", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/63.jpg", 2))
        listChallenge.add(ChallengeAnswer(3, "https://randomuser.me/api/portraits/men/11.jpg", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/women/43.jpg", 1))
        listChallenge.add(ChallengeAnswer(3, "https://youtu.be/tzzVwl9DDLM", "Lucas Boulet", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/13.jpg", 2))
        listChallenge.add(ChallengeAnswer(3, "https://youtu.be/tzzVwl9DDLM", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/23.jpg", 2))
        listChallenge.add(ChallengeAnswer(3, "https://youtu.be/tzzVwl9DDLM", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "https://randomuser.me/api/portraits/thumb/men/33.jpg", 2))

        val viewAdapter = HomeChallengeAdapter(listChallenge, mainActivity, activity)

        // apply the manager and the adapter to the permit_list
        fragment_home_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }


        fab_home_list_challenge.setOnFloatingActionsMenuUpdateListener(object : FloatingActionsMenu.OnFloatingActionsMenuUpdateListener {
            override fun onMenuExpanded() {
                //fragment_home_view.setBackgroundDrawable(ColorDrawable(Color.BLACK))
                //global_layout.setBackgroundDrawable(ColorDrawable(android.graphics.Color.BLACK))
                //global_layout.background.alpha = 128
            }

            override fun onMenuCollapsed() {
                //global_layout.background.alpha = 45
            }
        })

        floating_button_send_challenge.setOnClickListener {
            val intentRanking = Intent(mainActivity, LayDownChallengeActivity::class.java)
            startActivity(intentRanking)
        }

        floating_button_publish_challenge.setOnClickListener {
            val intentRanking = Intent(mainActivity, PublishChallengeActivity::class.java)
            startActivity(intentRanking)
        }


    }



    private fun fetchData() {

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
            activity = context
        } else {
            throw RuntimeException(context.toString() + " is not of type RequestPermitActivity")
        }
    }
}
