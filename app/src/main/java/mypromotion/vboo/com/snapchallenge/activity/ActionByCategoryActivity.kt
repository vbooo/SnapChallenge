package mypromotion.vboo.com.snapchallenge.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getActionsByCategory(): List<Action> {
        val listAction = arrayListOf<Action>()
        listAction.clear()
        listAction.add(Action(1, "Faire une piste rouge à l'escalade", 200, 20))
        listAction.add(Action(2, "Lever le plus possible au développer couché", 300, 20))
        listAction.add(Action(3, "Aller courir", 100, 20))
        return listAction
    }
}
