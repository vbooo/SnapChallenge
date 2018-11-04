package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_new_action.*
import mypromotion.vboo.com.snapchallenge.R

class NewActionActivity : AppCompatActivity() {

    companion object {
        const val ACTION_NAME = "actionName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_action)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activity_new_action_validate_layout.setOnClickListener {
            saveActionName(activity_new_action_name_action.text.toString())
        }
    }

    /**
     * Save the action name to the activity
     */
    fun saveActionName(name: String?) {
        val intent = intent
        intent.putExtra(ACTION_NAME, name)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
