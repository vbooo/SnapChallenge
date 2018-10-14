package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_my_challenge.view.*
import kotlinx.android.synthetic.main.item_ranking.view.*
import mypromotion.vboo.com.snapchallenge.R

class MyChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setActionName(name: String?) {
        view.item_my_challenge_action_name.text = name
    }

    // set user points
    fun setNbPoints(points: String?) {
        view.item_my_challenge_points.text = points
    }

    // set user points
    fun setTime(value: String?) {
        view.item_my_challenge_time.text = value
    }

    // set user points
    fun setActors(value: String?) {
        view.item_my_challenge_actors.text = value
    }
}