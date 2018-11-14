package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_action_by_category.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.ActionByCategoryAdapter
import mypromotion.vboo.com.snapchallenge.model.Action

class ActionByCategoryActivity : AppCompatActivity() {

    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_by_category)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        context = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        activity_action_by_category_recycler_view.setHasFixedSize(true)
        activity_action_by_category_recycler_view.layoutManager = LinearLayoutManager(context)
        activity_action_by_category_recycler_view.adapter = ActionByCategoryAdapter(this, getActionsByCategory())
    }

    private fun getActionsByCategory(): List<Action> {
        val listAction = arrayListOf<Action>()
        listAction.clear()
        listAction.add(Action(1, "Faire une piste rouge à l'escalade", 200, 20))
        listAction.add(Action(2, "Lever le plus possible au développer couché", 300, 20))
        listAction.add(Action(3, "Aller courir", 100, 20))
        return listAction
    }

    /**
     * Used for get the challenged user chosen in ChooseChallengedUserActivity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ListActionActivity.REQUEST_CODE_ACTION_CHOSE && resultCode == Activity.RESULT_OK) {
            intent.putExtra(ListActionActivity.ACTION_NAME, data?.getStringExtra(ListActionActivity.ACTION_NAME))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_category, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            R.id.action_add_new_action -> {
                val intent = Intent(context, NewActionActivity::class.java)
                startActivityForResult(intent, ListActionActivity.REQUEST_CODE_ACTION_CHOSE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
