package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.AnswerChallengeActivity
import mypromotion.vboo.com.snapchallenge.fragment.MyChallengesFragment
import mypromotion.vboo.com.snapchallenge.holder.MyChallengeHolder

class MyChallengeAdapter(private var dataSet: MutableList<MyChallengesFragment.Challenge>, var context: Context) : RecyclerView.Adapter<MyChallengeHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChallengeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_challenge, parent, false)
        return MyChallengeHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MyChallengeHolder, position: Int) {
        holder.setActionName(dataSet[position].action)
        holder.setStatus(dataSet[position].p)
        holder.setTime(dataSet[position].time)
        holder.setAuthorPicture(dataSet[position].author)
        holder.setChallengedUserPicture(dataSet[position].challengedUser)
        holder.getAnswerButton().setOnClickListener {
            val intent = Intent(context, AnswerChallengeActivity::class.java)
            intent.putExtra(AnswerChallengeActivity.NAME_ACTION, dataSet[position].action)
            intent.putExtra(AnswerChallengeActivity.IS_ANSWER_TO_SOMEONE, true)
            intent.putExtra(AnswerChallengeActivity.URL_AUTHOR, dataSet[position].author)
            context.startActivity(intent)
        }
    }
}