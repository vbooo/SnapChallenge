package mypromotion.vboo.com.snapchallenge.adapter

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
import mypromotion.vboo.com.snapchallenge.dataSource.ProfilDataSource
import mypromotion.vboo.com.snapchallenge.holder.ChallengeHolder
import mypromotion.vboo.com.snapchallenge.holder.ProfilHeaderHolder
import mypromotion.vboo.com.snapchallenge.model.User

class ProfilAdapter(var context: Context, var user: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Enum used to distinguish the different type of row in the recycler view */
    enum class ViewType { HEADER, ROW }

    private lateinit var _profilDataSource: ProfilDataSource

    var profilDataSource: ProfilDataSource? = null
        set(value) {
            if (value != null) {
                _profilDataSource = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.HEADER.ordinal) {
            ProfilHeaderHolder.create(parent, context)
        } else {
            ChallengeHolder.create(parent, context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (_profilDataSource.isHeaderAt(position)) {
            ViewType.HEADER.ordinal
        } else {
            ViewType.ROW.ordinal
        }
    }

    override fun getItemCount(): Int {
        return _profilDataSource.nbItem
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ProfilHeaderHolder) {
            val header = _profilDataSource.getHeaderAt(position)
            holder.setUserPicture(header.urlProfilPicture)
            holder.setUserName(header.name)
            holder.setNbPoints(header.points.toString())
            holder.setNbChallenges(header.nbChallenges.toString())
            holder.setNbSubscribe(header.nbSubscribers.toString())
        } else if (holder is ChallengeHolder) {
            val challengeUser = _profilDataSource.getChallengeUserAt(position)
            holder.setUserPicture(user.urlProfilPicture)
            holder.setChallengeDate(challengeUser.dateSubmission)
            holder.setChallengeTitle(user.name + " a publié un challenge")
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
                intent.putExtra(DetailMediaActivity.CHALLENGE_ANSWER, challengeUser)
                context.startActivity(intent)
            }

            holder.getCommentIcon().setOnClickListener {
                val intent = Intent(context, CommentMediaActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}