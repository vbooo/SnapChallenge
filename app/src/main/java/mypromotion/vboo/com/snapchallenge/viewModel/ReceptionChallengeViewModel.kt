package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import mypromotion.vboo.com.snapchallenge.R

class ReceptionChallengeViewModel(val context: Context) {

    val userName: String = "Francis Gros"

    /** Value title  */
    val userSentYouChallenge: String = context.resources.getString(R.string.user_sent_challenge, userName)

    /** Challenge name  */
    val nameChallenge: String = "Réussir une piste rouge à l'escalade"

    /** Challenge time  */
    val timeChallenge: String = "15 jours"
}