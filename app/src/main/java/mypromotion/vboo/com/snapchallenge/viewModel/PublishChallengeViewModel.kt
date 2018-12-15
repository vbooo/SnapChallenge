package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import android.view.View

class PublishChallengeViewModel(var context: Context,
                                var nameAction: String?,
                                var isAnswerToSomeone: Boolean,
                                var urlAuthor: String?,
                                var challengedUser: String?,
                                var answerIsPublic: Boolean) {

    fun placeholderAddActionVisibility(): Int {
        return if (nameAction == null) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun nameActionVisibility(): Int {
        return if (nameAction == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}