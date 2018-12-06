package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_my_challenge.view.*
import mypromotion.vboo.com.snapchallenge.R
import android.os.CountDownTimer
import android.R.attr.duration
import java.util.*


class MyChallengeHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    // set user name
    fun setActionName(name: String?) {
        view.item_my_challenge_action_name.text = name
    }

    // set user points
    fun setStatus(status: String?) {
        view.item_my_challenge_status.text = status
    }

    // set user points
    fun setTime(date: Date) {

        val duration = date.time - Date().time

        object : CountDownTimer(duration, 1000) {

            override fun onTick(millis: Long) {
                if (duration < 86400000) {
                    var millisUntilFinished = millis
                    val secondsInMilli: Long = 1000
                    val minutesInMilli = secondsInMilli * 60
                    val hoursInMilli = minutesInMilli * 60

                    val elapsedHours = millisUntilFinished / hoursInMilli
                    millisUntilFinished %= hoursInMilli

                    val elapsedMinutes = millisUntilFinished / minutesInMilli
                    millisUntilFinished %= minutesInMilli

                    val elapsedSeconds = millisUntilFinished / secondsInMilli

                    val formatedTime = String.format("%02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds)
                    view.item_my_challenge_time.text = formatedTime
                } else {
                    val days = (duration / (60*60*24*1000)) + 1
                    view.item_my_challenge_time.text = context.resources.getQuantityString(R.plurals.x_day_remaining, days.toInt(), days)
                }

            }

            override fun onFinish() {
                view.item_my_challenge_time.text = "00:00:00"
            }
        }.start()
    }

    // set user points
    fun setAuthorPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_my_challenge_author_picture)
    }

    fun setChallengedUserPicture(url: String?) {
        Picasso.get().load(url).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(view.item_my_challenge_challenged_user_picture)
    }

    fun getAnswerButton(): LinearLayout {
        return view.item_my_challenge_layout_button
    }
}