package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_lay_down_challenge.*
import kotlinx.android.synthetic.main.choose_action_dialog.*
import kotlinx.android.synthetic.main.choose_challenge_time_dialog.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge
import mypromotion.vboo.com.snapchallenge.viewModel.LayDownChallengeViewModel


class LayDownChallengeActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var viewModel: LayDownChallengeViewModel
    private val REQUEST_CODE_ADD_CHALLENGED_USER = 1
    private val REQUEST_CODE_ADD_NEW_ACTION = 2
    private val REQUEST_CODE_GET_ACTION_IN_LIST = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lay_down_challenge)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = LayDownChallengeViewModel(Challenge(), this)
        context = this

        updateAddActionLink()
        updatechallengedUserField()
        updateAddActionText()
        updateActionName()
        handleClickUserChallenged()
        handleSetChallengeTime()

        activity_lay_down_challenge_action_name.setOnClickListener {
            handleNewActionDialog()
        }

        updateLayDownChallenge()
    }

    private fun updateLayDownChallenge() {
        activity_lay_down_challenge_lay_down.setTextColor(viewModel.getLayDownChallengeTextColor())
        activity_lay_down_challenge_layout_lay_down.isClickable = viewModel.isLayDownChallengeLayoutClickable()
        if (activity_lay_down_challenge_layout_lay_down.isClickable) {
            activity_lay_down_challenge_layout_lay_down.setOnClickListener {
                finish()
            }
        }
    }

    private fun updateActionName() {
        activity_lay_down_challenge_action_name.visibility = viewModel.visibilityActionName()
        activity_lay_down_challenge_action_name.text = viewModel.challenge.tempActionName

    }

    private fun handleClickUserChallenged() {
        activity_lay_down_challenge_layout_challenged_user.setOnClickListener {
            val intent = Intent(context, ChooseChallengedUserActivity::class.java)
            intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, viewModel.challenge.idChallengedUser)
            startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
        }
    }

    private fun updateAddActionLink() {
        activity_lay_down_challenge_layout_add_layout.setOnClickListener {
            if (viewModel.challenge.idChallengedUser == null) {
                val intent = Intent(context, ChooseChallengedUserActivity::class.java)
                intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0)
                startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
            } else if (viewModel.challenge.idChallengedUser != null && viewModel.challenge.tempActionName == null) {
                handleNewActionDialog()
            } else if (viewModel.challenge.tempActionName != null) {
                handleTimeAction()
            }
        }
    }

    private fun handleTimeAction() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.choose_challenge_time_dialog)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    handleSetChallengeTime()
                }
                .setNegativeButton(R.string.no_time) { _, _ ->
                    viewModel.tempDay = 0
                    viewModel.tempHour = 0
                    viewModel.tempMinute = 0
                    handleSetChallengeTime()
                }
        val dialog = builder.create()
        dialog.show()

        dialog.choose_challenge_time_dialog_picker_day.maxValue = 364
        dialog.choose_challenge_time_dialog_picker_day.minValue = 0
        dialog.choose_challenge_time_dialog_picker_day.value = viewModel.tempDay


        dialog.choose_challenge_time_dialog_picker_hour.maxValue = 23
        dialog.choose_challenge_time_dialog_picker_hour.minValue = 0
        dialog.choose_challenge_time_dialog_picker_hour.value = viewModel.tempHour

        dialog.choose_challenge_time_dialog_picker_minute.maxValue = 59
        dialog.choose_challenge_time_dialog_picker_minute.minValue = 0
        dialog.choose_challenge_time_dialog_picker_minute.value = viewModel.tempMinute


        dialog.choose_challenge_time_dialog_picker_day.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempDay = newVal
        }

        dialog.choose_challenge_time_dialog_picker_hour.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempHour = newVal
        }

        dialog.choose_challenge_time_dialog_picker_minute.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempMinute = newVal
        }
    }

    private fun handleSetChallengeTime() {
        activity_lay_down_challenge_layout_add_layout.visibility = viewModel.visibilityLayoutAddAction()
        activity_lay_down_challenge_time_layout.visibility = viewModel.visibilityLayoutTime()
        activity_lay_down_challenge_time.text = viewModel.getTimeValue()
        activity_lay_down_challenge_time_layout.setOnClickListener {
            handleTimeAction()
        }
        updateAddActionText()
    }

    private fun handleNewActionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.choose_action_dialog)
        val dialog = builder.create()
        dialog.show()

        dialog.choose_action_dialog_in_list_layout.setOnClickListener {
            val intent = Intent(context, ListActionActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_GET_ACTION_IN_LIST)
            dialog.dismiss()
        }

        dialog.choose_action_dialog_new_action_layout.setOnClickListener {
            val intent = Intent(context, NewActionActivity::class.java)
            intent.putExtra(NewActionActivity.ACTION_NAME, viewModel.challenge.tempActionName)
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW_ACTION)
            dialog.dismiss()
        }
    }

    private fun updateAddActionText() {
        activity_lay_down_challenge_add_action.text = viewModel.addActionText()
        val animationFromBottom350 = TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationFromBottom350.duration = 370 // animation duration
        animationFromBottom350.fillAfter = true
        activity_lay_down_challenge_add_action.startAnimation(animationFromBottom350)

        val animationFromBottom300 = TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationFromBottom300.duration = 320 // animation duration
        animationFromBottom300.fillAfter = true
        activity_lay_down_challenge_add_img.startAnimation(animationFromBottom300)
    }

    /**
     * Used for get the challenged user chosen in ChooseChallengedUserActivity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ADD_CHALLENGED_USER && resultCode == Activity.RESULT_OK) {
            onChallengedUserChosen(data?.getIntExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0))
        } else if (requestCode == REQUEST_CODE_ADD_NEW_ACTION && resultCode == Activity.RESULT_OK) {
            onNewActionChosen(data?.getStringExtra(NewActionActivity.ACTION_NAME))
        } else if (requestCode == REQUEST_CODE_GET_ACTION_IN_LIST && resultCode == Activity.RESULT_OK) {
            onNewActionChosen(data?.getStringExtra(ListActionActivity.ACTION_NAME))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onNewActionChosen(actionName: String?) {
        viewModel.challenge.tempActionName = actionName
        updateActionName()
        updateAddActionText()
        handleLayDownButton()
        updateLayDownChallenge()
    }

    private fun handleLayDownButton() {

    }

    /**
     * Called when a challenged user has been chosen in the list
     */
    private fun onChallengedUserChosen(idChallengedUser: Int?) {
        viewModel.challenge.idChallengedUser = idChallengedUser

        // update the challenged user field
        updatechallengedUserField()
        updateAddActionText()
        updateLayDownChallenge()
    }

    private fun updatechallengedUserField() {
        activity_lay_down_challenge_layout_challenged_user.visibility = viewModel.challengedUserVisibility()
        activity_lay_down_challenged_user.text = viewModel.challengedUserName()

        val animationFromRight300 = TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationFromRight300.duration = 280 // animation duration
        animationFromRight300.fillAfter = true
        activity_lay_down_challenge_layout_challenged_user.startAnimation(animationFromRight300)

        val animationFromRight400 = TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationFromRight400.duration = 320 // animation duration
        animationFromRight400.fillAfter = true
        activity_lay_down_challenged_user.startAnimation(animationFromRight400)
    }

    private fun discardChallengeCreation() {
        val alertDiscardChallenge = AlertDialog.Builder(this).create()
        // set the message
        alertDiscardChallenge.setMessage(resources.getString(R.string.discardChallengeCreation))

        // set the AlertDialog buttons
        alertDiscardChallenge.setButton(AlertDialog.BUTTON_NEGATIVE, resources.getString(android.R.string.cancel)) {
            _, _ ->
        }
        alertDiscardChallenge.setButton(AlertDialog.BUTTON_POSITIVE, resources.getString(android.R.string.yes)) {
            _, _ -> finish()

        }
        alertDiscardChallenge.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            discardChallengeCreation()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        discardChallengeCreation()
        return true
    }
}
