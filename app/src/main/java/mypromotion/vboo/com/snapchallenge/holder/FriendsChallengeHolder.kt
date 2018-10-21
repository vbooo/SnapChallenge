package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_friend_challenge.view.*

class FriendsChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setActionName(name: String?) {
        view.item_friend_challenge_action_name.text = name
    }

    // set user points
    fun setStatus(status: String?) {
        view.item_friend_challenge_status.text = status
    }

    // set user points
    fun setTime(value: String?) {
        view.item_friend_challenge_time.text = value
    }

    // set user points
    fun setActors(value: String?) {
        view.item_friend_challenge_actors.text = value
    }
}