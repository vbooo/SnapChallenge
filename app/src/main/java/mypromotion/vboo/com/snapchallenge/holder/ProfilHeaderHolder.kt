package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profil_header.view.*
import mypromotion.vboo.com.snapchallenge.R

class ProfilHeaderHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context): ProfilHeaderHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_profil_header, parent, false)
            return ProfilHeaderHolder(view, context)
        }
    }

    // set user picture
    fun setUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_profil_header_user_image)
    }

    fun setUserName(value: String?) {
        view.item_profil_header_user_name.text = value
    }

    fun setNbPoints(value: String?) {
        view.item_profil_header_points.text = value
    }

    fun setNbChallenges(value: String?) {
        view.item_profil_header_nb_challenges.text = value
    }

    fun setNbSubscribe(value: String?) {
        view.item_profil_header_nb_subscribers.text = value
    }
}