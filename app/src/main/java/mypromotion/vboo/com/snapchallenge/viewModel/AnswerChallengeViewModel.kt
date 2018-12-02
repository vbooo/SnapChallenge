package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import android.view.View
import mypromotion.vboo.com.snapchallenge.R

class AnswerChallengeViewModel(var context: Context,
                               var nameAction: String?,
                               var isAnswerToSomeone: Boolean,
                               var urlAuthor: String?,
                               var challengedUser: String?,
                               var answerIsPublic: Boolean) {

    fun visibilitySecondLineHeader(): Int {
        return if (isAnswerToSomeone) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun privacyValue(): String {
        return if (answerIsPublic) {
            context.resources.getString(R.string.public_word).toUpperCase()
        } else {
            context.resources.getString(R.string.private_word).toUpperCase()
        }
    }

    fun getBackgroundPrivacy(): Int {
        return if (answerIsPublic) {
            R.drawable.background_blue_button
        } else {
            R.drawable.background_red_button
        }
    }

    fun publicCheckboxIsChecked(): Boolean {
        return answerIsPublic
    }

    fun privateCheckboxIsChecked(): Boolean {
        return !answerIsPublic
    }
}