package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.holder.RankingHolder
import mypromotion.vboo.com.snapchallenge.activity.RankingActivity

class RankingAdapter(var dataSet: MutableList<RankingActivity.User>, var context: Context) : RecyclerView.Adapter<RankingHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RankingHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_ranking, parent, false)

        return RankingHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RankingHolder?, position: Int) {
        holder?.setUserName(dataSet[position].name)
        holder?.setUserPoints(dataSet[position].points)
        holder?.setUserPicture(null)
    }

}