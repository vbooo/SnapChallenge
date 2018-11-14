package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lay_down_challenge.*
import kotlinx.android.synthetic.main.activity_new_action.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.viewModel.NewActionViewModel

class NewActionActivity : AppCompatActivity() {

    companion object {
        const val ACTION_NAME = "actionName"
    }

    lateinit var viewModel: NewActionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_action)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = NewActionViewModel(this, intent.getStringExtra(ACTION_NAME))

        activity_new_action_name_action.setText(viewModel.actionName)

        updateValidateAction()
        activity_new_action_name_action.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.actionName = s.toString()
                updateValidateAction()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    /**
     * Save the action name to the activity
     */
    private fun saveActionName(name: String?) {
        val intent = intent
        intent.putExtra(ACTION_NAME, name)
        setResult(Activity.RESULT_OK, intent)
        if (activity_new_action_switch.isChecked) {
            Toast.makeText(this, resources.getString(R.string.confirmationActionSentToApp),
                    Toast.LENGTH_LONG).show()
        }
        finish()
    }

    private fun updateValidateAction() {
        activity_new_action_validate.setTextColor(viewModel.getValidateTextColor())
        activity_new_action_validate_layout.isClickable = viewModel.isLayDownChallengeLayoutClickable()
        if (activity_new_action_validate_layout.isClickable) {
            activity_new_action_validate_layout.setOnClickListener {
                saveActionName(activity_new_action_name_action.text.toString())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
