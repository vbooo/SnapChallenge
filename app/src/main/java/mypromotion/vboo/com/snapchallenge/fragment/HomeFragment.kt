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
import kotlinx.android.synthetic.main.fragment_home.*
import mypromotion.vboo.com.snapchallenge.MainActivity
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.HomeChallengeAdapter
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var mainActivity: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listChallenge = arrayListOf<ChallengeAnswer>()
        listChallenge.clear()
        listChallenge.add(ChallengeAnswer(1, "url", "Fabien Bouvet", "Ice bucket challenge !", "", Date(), 7, 32, 12, 8, "", 2))
        listChallenge.add(ChallengeAnswer(2, "url", "Francis Gros", "Bisous en haut de la Tour Eiffel", "", Date(), 7, 32, 12, 8, "", 1))
        listChallenge.add(ChallengeAnswer(3, "url", "Nicolas Fornerod", "Voici mon pari de ouf haha", "", Date(), 7, 32, 12, 8, "", 2))

        val viewAdapter = HomeChallengeAdapter(listChallenge, mainActivity)

        // apply the manager and the adapter to the permit_list
        fragment_home_recycler_view.apply {
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
