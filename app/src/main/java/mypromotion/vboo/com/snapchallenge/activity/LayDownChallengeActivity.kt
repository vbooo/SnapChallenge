package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lay_down_challenge.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge

class LayDownChallengeActivity : AppCompatActivity() {

    var challenge: Challenge? = null
    private val REQUEST_CODE_ADD_CHALLENGED_USER = 1
    // code for choose challenged user activity result
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lay_down_challenge)
        challenge = Challenge()

        activity_lay_down_challenge_layout_add_user.setOnClickListener {
            val intent = Intent(this, ChooseChallengedUserActivity::class.java)
            intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, challenge?.idChallengedUser)
            startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
        }
    }

    /**
     * Used for get the challenged user chosen in ChooseChallengedUserActivity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // update UI and data regarding the challenged user
            onChallengedUserChosen(data?.getIntExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Called when a challenged user has been chosen in the list
     */
    private fun onChallengedUserChosen(idChallengedUser: Int?) {

        challenge?.idChallengedUser = idChallengedUser

        // update the challenged user field
        updatechallengedUserField()
    }

    private fun updatechallengedUserField() {

    }
}
