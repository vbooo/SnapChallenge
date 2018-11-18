package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comment.view.*
import mypromotion.vboo.com.snapchallenge.R
import java.util.*

class CommentMediaHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setUserPicture(url: String?) {
        if (url != null && !url.isEmpty()) {
            Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                    .into(view.item_comment_challenge_user_picture)
        }
    }

    fun setUserName(name: String?) {
        view.item_comment_challenge_title.text = name
    }

    fun setComment(name: String?) {
        view.item_comment_value.text = name
    }

    fun setNbLike(name: String?) {
        view.item_comment_nbLike.text = name
    }

    fun setDateComment(date: Date?) {
        view.item_comment_challenge_date.text = "Hier"
    }

}