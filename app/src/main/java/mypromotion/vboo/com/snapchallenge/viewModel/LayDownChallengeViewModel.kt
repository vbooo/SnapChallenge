package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import android.view.View
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge


class LayDownChallengeViewModel(val challenge: Challenge, val context: Context) {

    fun visibilityActionName(): Int {
        return if (challenge.tempActionName == null || challenge.tempActionName!!.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun addActionText(): String {
        return if (challenge.idChallengedUser == null) {
             context.resources.getString(R.string.choose_user_to_lay_down)
        } else if (challenge.idChallengedUser != null && challenge.tempActionName == null) {
            context.resources.getString(R.string.choose_action_to_realize)
        } else if (challenge.tempActionName != null && !challenge.tempActionName!!.isEmpty()) {
            context.resources.getString(R.string.choose_challenge_time)
        } else {
            ""
        }
    }

    fun challengedUserVisibility(): Int {
        return if (challenge.idChallengedUser == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun challengedUserName(): String {
        return if (challenge.idChallengedUser != null) {
            "Francis Gros"
        } else {
            ""
        }
    }
}