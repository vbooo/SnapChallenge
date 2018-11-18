package mypromotion.vboo.com.snapchallenge.viewModel

import android.view.View
import mypromotion.vboo.com.snapchallenge.AnswerChallengeType
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer

class DetailMediaViewModel(var challengeAnswer: ChallengeAnswer) {

    fun pictureVisibility(): Int {
        return if (challengeAnswer != null && challengeAnswer.typeChallenge == AnswerChallengeType.photo.ordinal) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun videoVisibility(): Int {
        return if (challengeAnswer != null && challengeAnswer.typeChallenge == AnswerChallengeType.video.ordinal) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun getNbLike(): Int {
        return challengeAnswer.nbLike
    }

    fun getNbComment(): Int {
        return challengeAnswer.nbComment
    }

    fun getNbShare(): Int {
        return challengeAnswer.nbShare
    }
}