package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge


class LayDownChallengeViewModel(val challenge: Challenge, val context: Context, var tempDay: Int = 0, var tempHour: Int = 0, var tempMinute: Int = 0) {

    fun visibilityActionName(): Int {
        return if (challenge.tempActionName == null || challenge.tempActionName!!.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun getLayDownChallengeTextColor(): Int {
        return if (challenge.idChallengedUser != null && challenge.tempActionName != null) {
            ContextCompat.getColor(context, R.color.blue_theme)
        } else {
            ContextCompat.getColor(context, R.color.blue_theme_opacity)
        }
    }

    fun isLayDownChallengeLayoutClickable(): Boolean {
        return challenge.idChallengedUser != null && challenge.tempActionName != null
    }

    fun visibilityLayoutTime(): Int {
        return if (tempDay == 0 && tempHour == 0 && tempMinute == 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun visibilityLayoutAddAction(): Int {
        return if (tempDay == 0 && tempHour == 0 && tempMinute == 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun getTimeValue(): String {
        return if (tempDay == 0 && tempHour == 0 && tempMinute != 0) {
            context.resources.getQuantityString(R.plurals.x_minute, tempMinute, tempMinute)
        } else if (tempDay == 0 && tempHour != 0 && tempMinute == 0) {
            context.resources.getQuantityString(R.plurals.x_hour, tempHour, tempHour)
        } else if (tempDay != 0 && tempHour == 0 && tempMinute == 0) {
            context.resources.getQuantityString(R.plurals.x_day, tempDay, tempDay)
        } else if (tempDay == 0 && tempHour != 0 && tempMinute != 0) {
            context.resources.getQuantityString(R.plurals.x_hour, tempHour, tempHour) +
                    " " + context.resources.getQuantityString(R.plurals.x_minute, tempMinute, tempMinute)
        } else if (tempDay != 0 && tempHour == 0 && tempMinute != 0) {
            context.resources.getQuantityString(R.plurals.x_day, tempDay, tempDay) +
                    " " + context.resources.getQuantityString(R.plurals.x_minute, tempMinute, tempMinute)
        } else if (tempDay != 0 && tempHour != 0 && tempMinute == 0) {
            context.resources.getQuantityString(R.plurals.x_day, tempDay, tempDay) +
                    " " + context.resources.getQuantityString(R.plurals.x_hour, tempHour, tempHour)
        } else if (tempDay != 0 && tempHour != 0 && tempMinute != 0) {
            context.resources.getQuantityString(R.plurals.x_day, tempDay, tempDay) + " " +
                     context.resources.getQuantityString(R.plurals.x_hour, tempHour, tempHour) + " " +
            context.resources.getQuantityString(R.plurals.x_minute, tempMinute, tempMinute)
        } else {
            ""
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