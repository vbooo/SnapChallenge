package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW_ACTION)
            dialog.dismiss()
        }
    }

    private fun updateAddActionText() {
        activity_lay_down_challenge_add_action.text = viewModel.addActionText()
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
    }

    /**
     * Called when a challenged user has been chosen in the list
     */
    private fun onChallengedUserChosen(idChallengedUser: Int?) {
        viewModel.challenge.idChallengedUser = idChallengedUser

        // update the challenged user field
        updatechallengedUserField()
        updateAddActionText()
    }

    private fun updatechallengedUserField() {
        activity_lay_down_challenge_layout_challenged_user.visibility = viewModel.challengedUserVisibility()
        activity_lay_down_challenged_user.text = viewModel.challengedUserName()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
