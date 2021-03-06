package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_challenge.view.*
import mypromotion.vboo.com.snapchallenge.R
import java.util.*


class ChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context): ChallengeHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_challenge, parent, false)
            return ChallengeHolder(view, context)
        }
    }

    // set user name
    fun setUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_home_challenge_user_picture)
    }

    fun getUserPicture(): CircleImageView {
        return view.item_home_challenge_user_picture
    }

    fun setChallengeTitle(title: String?) {
        view.item_home_challenge_title.text = title
    }

    fun setChallengeDate(date: Date?) {
        view.item_home_challenge_date.text = "Il y a 2 heures".toUpperCase()
    }

    fun setChallengeDescription(desc: String?) {
        view.item_home_challenge_description.text = desc
    }

    fun getPhotoContainer(): ImageView {
        return view.item_home_challenge_container_image
    }

    fun getVideoContainer(): PlayerView {
        return view.item_home_challenge_container_video
    }

    fun handleVideoView(path: String?): SimpleExoPlayer? {
        return try {
            val player = ExoPlayerFactory.newSimpleInstance(context)
            view.item_home_challenge_container_video.player = player

            val videoURI = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4")
            val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")
            val extractorsFactory = DefaultExtractorsFactory()
            val mediaSource = ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)

            // Prepare the player with the source.
            player.prepare(mediaSource)
            player.playWhenReady = true
            player.repeatMode = Player.REPEAT_MODE_ALL
            player?.volume = 0f
            player
        } catch (e: Exception) {
            Log.e("MainActivity", " exoplayer error " + e.toString())
            null
        }

    }

    fun handleMuteButton(player: SimpleExoPlayer?) {
        updateVolumeButton(player)
        view.item_home_challenge_mute_button.setOnClickListener {
            if (player?.volume == 0f) {
                player.volume = 1f
            } else {
                player?.volume = 0f
            }
            updateVolumeButton(player)
        }
    }

    fun updateVolumeButton(player: SimpleExoPlayer?) {
        if (player?.volume == 0f) {
            view.item_home_challenge_mute_button.setImageDrawable(context.resources.getDrawable(R.drawable.baseline_volume_off_white_24))
        } else {
            view.item_home_challenge_mute_button.setImageDrawable(context.resources.getDrawable(R.drawable.baseline_volume_up_white_24))
        }
    }

    fun getMuteButton(): ImageView {
        return view.item_home_challenge_mute_button
    }



    fun setNbLike(count: Int?) {
        view.item_home_challenge_nb_like.text = count.toString()
    }

    fun setNbComment(count: Int?) {
        view.item_home_challenge_nb_comment.text = count.toString()
    }

    fun setNbShare(count: Int?) {
        view.item_home_challenge_nb_share.text = count.toString()
    }

    fun setNbChallengers(value: String?) {
        view.item_home_challenge_nb_challengers.text = value.toString()
    }

    fun setUrlPicture(url: String?) {
        Picasso.get().load(url)
                .into(view.item_home_challenge_container_image)
    }

    fun getNbChallengerTextView(): TextView {
        return view.item_home_challenge_nb_challengers
    }

    fun getCommentIcon(): ImageView {
        return view.item_home_challenge_comment_picture
    }

    fun hideAnswerChallenge() {
        view.item_home_challenge_answer_challenge_layout.visibility = View.GONE

    }

    fun hideNbChallengers() {
        view.item_home_challenge_nb_challengers.visibility = View.GONE
    }

    fun getAnswerChallengeLayout(): LinearLayout {
        return view.item_home_challenge_answer_challenge_layout
    }

}