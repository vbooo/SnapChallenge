package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_my_challenge.view.*
import mypromotion.vboo.com.snapchallenge.R

class MyChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setActionName(name: String?) {
        view.item_my_challenge_action_name.text = name
    }

    // set user points
    fun setStatus(status: String?) {
        view.item_my_challenge_status.text = status
    }

    // set user points
    fun setTime(value: String?) {
        view.item_my_challenge_time.text = value
    }

    // set user points
    fun setAuthorPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_my_challenge_author_picture)
    }

    fun setChallengedUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_my_challenge_challenged_user_picture)
    }

    fun getAnswerButton(): LinearLayout {
        return view.item_my_challenge_layout_button
    }
}