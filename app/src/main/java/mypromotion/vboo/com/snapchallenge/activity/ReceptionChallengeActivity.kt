package mypromotion.vboo.com.snapchallenge.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reception_challenge.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.viewModel.ReceptionChallengeViewModel

class ReceptionChallengeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reception_challenge)

        val viewModel = ReceptionChallengeViewModel(this)
        activity_reception_challenge_user_sent_challenge.text = viewModel.userSentYouChallenge
        activity_reception_challenge_name_challenge.text  = viewModel.nameChallenge
        activity_reception_challenge_time_challenge.text = viewModel.timeChallenge
    }
}
