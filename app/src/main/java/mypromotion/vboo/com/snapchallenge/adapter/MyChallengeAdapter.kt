package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.fragment.MyChallengesFragment
import mypromotion.vboo.com.snapchallenge.holder.MyChallengeHolder

class MyChallengeAdapter(var dataSet: MutableList<MyChallengesFragment.Challenge>, var context: Context) : RecyclerView.Adapter<MyChallengeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyChallengeHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_challenge, parent, false)
        return MyChallengeHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MyChallengeHolder?, position: Int) {
        holder?.setActionName(dataSet[position].a)
        holder?.setNbPoints(dataSet[position].p)
        holder?.setTime(dataSet[position].t)
        holder?.setActors(dataSet[position].actor)
    }
}