package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_lay_down_challenge.*
import kotlinx.android.synthetic.main.choose_action_dialog.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge

class LayDownChallengeActivity : AppCompatActivity() {

    var challenge: Challenge? = null
    var context: Context? = null
    private val REQUEST_CODE_ADD_CHALLENGED_USER = 1
    private val REQUEST_CODE_ADD_NEW_ACTION = 2
    // code for choose challenged user activity result
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lay_down_challenge)

        context = this
        challenge = Challenge()

        updateAddActionLink()
        updatechallengedUserField()
        updateAddActionText()
        updateActionName()
        handleClickUserChallenged()
    }

    private fun updateActionName() {
        if (challenge?.tempActionName == null || challenge?.tempActionName!!.isEmpty()) {
            activity_lay_down_challenge_action_name.visibility = View.GONE
        } else {
            activity_lay_down_challenge_action_name.visibility = View.VISIBLE
            activity_lay_down_challenge_action_name.text = challenge?.tempActionName
        }
    }

    private fun handleClickUserChallenged() {
        activity_lay_down_challenge_layout_challenged_user.setOnClickListener {
            val intent = Intent(context, ChooseChallengedUserActivity::class.java)
            intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, challenge?.idChallengedUser)
            startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
        }
    }

    private fun updateAddActionLink() {

            activity_lay_down_challenge_layout_add_user.setOnClickListener {
                if (challenge?.idChallengedUser != null) {
                    handleNewActionDialog()
                } else {
                    val intent = Intent(context, ChooseChallengedUserActivity::class.java)
                    intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0)
                    startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
                }
        }

    }

    private fun handleNewActionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.choose_action_dialog)
        val dialog = builder.create()
        dialog.show()

        dialog.choose_action_dialog_new_action_layout.setOnClickListener {
            val intent = Intent(context, NewActionActivity::class.java)
            //intent.putExtra(CreateNewActionActivity.NAME_ACTION, "")
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW_ACTION)
            dialog.dismiss()
        }

    }

    private fun updateAddActionText() {
        if (challenge?.idChallengedUser == null) {
            activity_lay_down_challenge_add_action.text = resources.getString(R.string.choose_user_to_lay_down)
        } else if (challenge?.idChallengedUser != null && challenge?.tempActionName == null) {
            activity_lay_down_challenge_add_action.text = resources.getString(R.string.choose_action_to_realize)
        } else if (challenge?.tempActionName != null) {
            activity_lay_down_challenge_add_action.text = resources.getString(R.string.choose_challenge_time)
        }

    }

    /**
     * Used for get the challenged user chosen in ChooseChallengedUserActivity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ADD_CHALLENGED_USER && resultCode == Activity.RESULT_OK) {

            // update UI and data regarding the challenged user
            onChallengedUserChosen(data?.getIntExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0))
        } else if (requestCode == REQUEST_CODE_ADD_NEW_ACTION && resultCode == Activity.RESULT_OK) {
            onNewActionChosen(data?.getStringExtra(NewActionActivity.ACTION_NAME))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onNewActionChosen(actionName: String?) {
        challenge?.tempActionName = actionName
        updateActionName()
        updateAddActionText()
    }

    /**
     * Called when a challenged user has been chosen in the list
     */
    private fun onChallengedUserChosen(idChallengedUser: Int?) {

        challenge?.idChallengedUser = idChallengedUser

        // update the challenged user field
        updatechallengedUserField()
        updateAddActionText()
    }

    private fun updatechallengedUserField() {
        if (challenge?.idChallengedUser == null) {
            activity_lay_down_challenge_layout_challenged_user.visibility = View.GONE
        } else {
            activity_lay_down_challenge_layout_challenged_user.visibility = View.VISIBLE
            activity_lay_down_challenged_user.text = "Francis Gros"
        }
    }
}
