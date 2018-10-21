package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.fragment.MyChallengesFragment
import mypromotion.vboo.com.snapchallenge.holder.FriendsChallengeHolder

class FriendChallengesAdapter(var dataSet: MutableList<MyChallengesFragment.Challenge>, var context: Context) : RecyclerView.Adapter<FriendsChallengeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendsChallengeHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_friend_challenge, parent, false)
        return FriendsChallengeHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: FriendsChallengeHolder?, position: Int) {
        holder?.setActionName(dataSet[position].a)
        holder?.setStatus(dataSet[position].p)
        holder?.setTime(dataSet[position].t)
        holder?.setActors(dataSet[position].actor)
    }
}