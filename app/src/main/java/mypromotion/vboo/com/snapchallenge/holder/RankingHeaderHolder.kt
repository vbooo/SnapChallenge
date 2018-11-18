package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ranking.view.*
import kotlinx.android.synthetic.main.item_ranking_header.view.*
import mypromotion.vboo.com.snapchallenge.R

class RankingHeaderHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context): RankingHeaderHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_ranking_header, parent, false)
            return RankingHeaderHolder(view, context)
        }
    }

    // set user name
    fun setFirstUserName(name: String?) {
        view.item_ranking_header_first_name.text = name
    }

    fun setSecondUserName(name: String?) {
        view.item_ranking_header_second_name.text = name
    }

    fun setThirdUserName(name: String?) {
        view.item_ranking_header_third_name.text = name
    }

    fun setFirstUserPoints(points: Int) {
        val pointString = context.resources.getQuantityString(R.plurals.x_point, points, points)
        view.item_ranking_header_first_nb_points.text = pointString
    }

    fun setSecondUserPoints(points: Int) {
        val pointString = context.resources.getQuantityString(R.plurals.x_point, points, points)
        view.item_ranking_header_second_nb_points.text = pointString
    }

    fun setThirdUserPoints(points: Int) {
        val pointString = context.resources.getQuantityString(R.plurals.x_point, points, points)
        view.item_ranking_header_third_nb_points.text = pointString
    }

    // set user picture
    fun setFirstUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_ranking_header_first_picture)
    }

    fun setSecondUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_ranking_header_second_picture)
    }

    fun setThirdUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_ranking_header_third_picture)
    }
}