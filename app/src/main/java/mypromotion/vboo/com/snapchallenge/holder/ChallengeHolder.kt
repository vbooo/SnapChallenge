package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_challenge.view.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.R.id.item_home_challenge_answer_challenge_layout
import java.util.*

class ChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context): ChallengeHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_challenge, parent, false)
            return ChallengeHolder(view, context)
        }
    }

    // set user name
    fun setUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_home_challenge_user_picture)
    }

    fun getUserPicture(): CircleImageView {
        return view.item_home_challenge_user_picture
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

    fun setUrlPicture(url: String?) {
        Picasso.get().load(url)
                .into(view.item_home_challenge_container_image)
    }

    fun getNbChallengerTextView(): TextView {
        return view.item_home_challenge_nb_challengers
    }

    fun getCommentIcon(): ImageView {
        return view.item_home_challenge_comment_picture
    }

    fun hideAnswerChallenge() {
        view.item_home_challenge_answer_challenge_layout.visibility = View.GONE
        view.item_home_challenge_separator_footer.visibility = View.GONE
    }

    fun hideNbChallengers() {
        view.item_home_challenge_nb_challengers.visibility = View.GONE
    }

}