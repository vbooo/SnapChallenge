package mypromotion.vboo.com.snapchallenge.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.AnswerChallengeType
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ActionChallengersActivity
import mypromotion.vboo.com.snapchallenge.activity.CommentMediaActivity
import mypromotion.vboo.com.snapchallenge.activity.DetailMediaActivity
import mypromotion.vboo.com.snapchallenge.dataSource.ActionChallengersDataSource
import mypromotion.vboo.com.snapchallenge.holder.ActionChallengersHeaderHolder
import mypromotion.vboo.com.snapchallenge.holder.ChallengeHolder

class ActionChallengersAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Enum used to distinguish the different type of row in the recycler view */
    enum class ViewType { HEADER, ROW }

    private lateinit var _actionChallengersDataSource: ActionChallengersDataSource

    var profilDataSource: ActionChallengersDataSource? = null
        set(value) {
            if (value != null) {
                _actionChallengersDataSource = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.HEADER.ordinal) {
            ActionChallengersHeaderHolder.create(parent, context)
        } else {
            ChallengeHolder.create(parent, context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (_actionChallengersDataSource.isHeaderAt(position)) {
            ViewType.HEADER.ordinal
        } else {
            ViewType.ROW.ordinal
        }
    }

    override fun getItemCount(): Int {
        return _actionChallengersDataSource.nbItem
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ActionChallengersHeaderHolder) {
            val header = _actionChallengersDataSource.getHeaderAt(position)
            holder.setActionName(header.nameAction)
            holder.setNbChallengers(header.nbChallengers)

        } else if (holder is ChallengeHolder) {
            val challengeUser = _actionChallengersDataSource.getChallengeUserAt(position)
            holder.setUserPicture(challengeUser.urlOwner)
            holder.setChallengeDate(challengeUser.dateSubmission)
            holder.setChallengeTitle(challengeUser.nameOwner)
            holder.setChallengeDescription(challengeUser.description)

            if (challengeUser.typeChallenge == AnswerChallengeType.video.ordinal) {
                holder.getPhotoContainer().visibility = View.GONE
                holder.getVideoContainer().visibility = View.VISIBLE
            } else {
                holder.getPhotoContainer().visibility = View.VISIBLE
                holder.getVideoContainer().visibility = View.GONE
            }

            holder.setNbLike(challengeUser.nbLike)
            holder.setNbComment(challengeUser.nbComment)
            holder.setNbShare(challengeUser.nbShare)

            holder.setUrlPicture(challengeUser.urlMedia)

            holder.setNbChallengers(context.resources.getQuantityString(R.plurals.x_challenger, challengeUser.nbChallengers, challengeUser.nbChallengers))

            holder.getNbChallengerTextView()?.setOnClickListener {
                val intent = Intent(context, ActionChallengersActivity::class.java)
                context.startActivity(intent)
            }

            holder.getPhotoContainer().setOnClickListener {
                val intent = Intent(context, DetailMediaActivity::class.java)
                val options = ActivityOptions
                        .makeSceneTransitionAnimation(context as Activity, holder.getPhotoContainer() as View, context.resources.getString(R.string.mediaImageTransition))
                intent.putExtra(DetailMediaActivity.CHALLENGE_ANSWER, challengeUser)
                context.startActivity(intent, options.toBundle())
            }

            holder.getCommentIcon().setOnClickListener {
                val intent = Intent(context, CommentMediaActivity::class.java)
                context.startActivity(intent)
            }
            holder.hideAnswerChallenge()
            holder.hideNbChallengers()
        }
    }
}