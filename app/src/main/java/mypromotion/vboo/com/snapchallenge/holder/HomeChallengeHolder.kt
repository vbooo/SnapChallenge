package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_challenge.view.*
import mypromotion.vboo.com.snapchallenge.R
import java.util.*

class HomeChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setUserPicture(url: String?) {
        Picasso.get().load(R.drawable.user_default).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_home_challenge_user_picture)
    }

    fun setChallengeTitle(title: String?) {
        view.item_home_challenge_title.text = title
    }

    fun setChallengeDate(date: Date?) {
        view.item_home_challenge_date.text = "Il y a une heure"
    }

    fun setChallengeDescription(desc: String?) {
        view.item_home_challenge_description.text = desc
    }

    fun getPhotoContainer(): ImageView {
        return view.item_home_challenge_container_image
    }

    fun getVideoContainer(): VideoView {
        return view.item_home_challenge_container_video
    }

    fun setNbLike(count: Int?) {
        view.item_home_challenge_nb_like.text = count.toString()
    }

    fun setNbComment(count: Int?) {
        view.item_home_challenge_nb_comment.text = count.toString()
    }

    fun setNbShare(count: Int?) {
        view.item_home_challenge_nb_share.text = count.toString()
    }

    fun setNbChallengers(value: String?) {
        view.item_home_challenge_nb_challengers.text = value.toString()
    }
}