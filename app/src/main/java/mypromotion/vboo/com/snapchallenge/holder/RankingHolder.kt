package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ranking.view.*
import mypromotion.vboo.com.snapchallenge.R

class RankingHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setUserName(name: String?) {
        view.item_ranking_user_name.text = name
    }

    // set user points
    fun setUserPoints(points: String?) {
        view.item_ranking_user_points.text = points
    }

    // set user picture
    fun setUserPicture(url: String?) {

        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_ranking_user_image)
    }


}