package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list_action.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.CategoryActionsAdapter
import mypromotion.vboo.com.snapchallenge.model.CategoryTemp

class ListCategoryActivity : AppCompatActivity() {

    private var context: Context? = null

    companion object {
        const val ACTION_NAME = "actionName"
        const val REQUEST_CODE_ACTION_CHOSE = 4
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
        activity_list_action_recycler_view.layoutManager = GridLayoutManager(context, 2)
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
        listCategory.add(CategoryTemp("Divers", "882 challenges"))
        listCategory.add(CategoryTemp("Sport", "782 challenges"))
        listCategory.add(CategoryTemp("Couple", "682 challenges"))
        listCategory.add(CategoryTemp("Animaux", "602 challenges"))
        listCategory.add(CategoryTemp("Sortie", "602 challenges"))
        listCategory.add(CategoryTemp("Famille", "582 challenges"))
        listCategory.add(CategoryTemp("Voyage", "282 challenges"))
        listCategory.add(CategoryTemp("Vacances", "212 challenges"))
        listCategory.add(CategoryTemp("Alimentation", "182 challenges"))
        listCategory.add(CategoryTemp("Maison", "45 challenges"))
        return listCategory
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ACTION_CHOSE && resultCode == Activity.RESULT_OK) {
            saveActionName(data?.getStringExtra(ACTION_NAME))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
                startActivityForResult(intent, REQUEST_CODE_ACTION_CHOSE)
                true
            }

            R.id.action_search_action -> {
                val intent = Intent(context, SearchActionActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
