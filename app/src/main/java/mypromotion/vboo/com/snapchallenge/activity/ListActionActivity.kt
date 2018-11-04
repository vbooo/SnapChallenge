package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list_action.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.CategoryActionsAdapter
import mypromotion.vboo.com.snapchallenge.model.CategoryTemp

class ListActionActivity : AppCompatActivity() {

    private var context: Context? = null

    companion object {
        const val ACTION_NAME = "actionName"
        const val REQUEST_CODE_TEMP = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_action)
        context = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecyclerView()
    }

    /**
     * Users recyclerview initialization
     */
    private fun initRecyclerView() {
        activity_list_action_recycler_view.setHasFixedSize(true)
        activity_list_action_recycler_view.layoutManager = LinearLayoutManager(context)
        activity_list_action_recycler_view.adapter = CategoryActionsAdapter(this, getCategory())
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

    private fun getCategory(): List<CategoryTemp> {
        val listCategory = arrayListOf<CategoryTemp>()
        listCategory.clear()
        listCategory.add(CategoryTemp("Divers", "582 actions"))
        listCategory.add(CategoryTemp("Sport", "482 actions"))
        listCategory.add(CategoryTemp("Famille", "382 actions"))
        listCategory.add(CategoryTemp("Musique", "372 actions"))
        listCategory.add(CategoryTemp("Voyage", "282 actions"))
        listCategory.add(CategoryTemp("Vacances", "212 actions"))
        listCategory.add(CategoryTemp("Cuisine", "182 actions"))
        listCategory.add(CategoryTemp("Maison", "45 actions"))
        return listCategory
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_TEMP && resultCode == Activity.RESULT_OK) {
            saveActionName(data?.getStringExtra(ACTION_NAME))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
