package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.AnswerChallengeType
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ActionChallengersActivity
import mypromotion.vboo.com.snapchallenge.activity.DetailMediaActivity
import mypromotion.vboo.com.snapchallenge.activity.PublishChallengeActivity
import mypromotion.vboo.com.snapchallenge.holder.HomeChallengeHolder
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import java.util.*


class HomeChallengeAdapter(private var dataSet: MutableList<ChallengeAnswer>, var context: Context) : RecyclerView.Adapter<HomeChallengeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeChallengeHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_home_challenge, parent, false)
        return HomeChallengeHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: HomeChallengeHolder?, position: Int) {
        holder?.setUserPicture(dataSet[position].urlOwner)
        holder?.setChallengeDate(Date())
        holder?.setChallengeTitle(dataSet[position].nameOwner + " a publié un challenge")
        holder?.setChallengeDescription(dataSet[position].description)

        if (dataSet[position].typeChallenge == AnswerChallengeType.video.ordinal) {
            holder?.getPhotoContainer()?.visibility = View.GONE
            holder?.getVideoContainer()?.visibility = View.VISIBLE
        } else {
            holder?.getPhotoContainer()?.visibility = View.VISIBLE
            holder?.getVideoContainer()?.visibility = View.GONE
        }

        holder?.setNbLike(dataSet[position].nbLike)
        holder?.setNbComment(dataSet[position].nbComment)
        holder?.setNbShare(dataSet[position].nbShare)

        holder?.setUrlPicture(dataSet[position].url)

        holder?.setNbChallengers(context.resources.getQuantityString(R.plurals.x_challenger, dataSet[position].nbChallengers, dataSet[position].nbChallengers))

        holder?.getNbChallengerTextView()?.setOnClickListener {
            val intent = Intent(context, ActionChallengersActivity::class.java)
            context.startActivity(intent)
        }

        holder?.getPhotoContainer()?.setOnClickListener {
            val intent = Intent(context, DetailMediaActivity::class.java)
            context.startActivity(intent)
        }

    }
}