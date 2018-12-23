package mypromotion.vboo.com.snapchallenge.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ActionByCategoryActivity
import mypromotion.vboo.com.snapchallenge.activity.ListCategoryActivity
import mypromotion.vboo.com.snapchallenge.holder.ActionHolder
import mypromotion.vboo.com.snapchallenge.model.Action

class ActionByCategoryAdapter (private val activity: ActionByCategoryActivity, private val actions: List<Action>) : RecyclerView.Adapter<ActionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_action, parent, false)
        return ActionHolder(view)
    }

    override fun getItemCount(): Int {
        return actions.size
    }

    override fun onBindViewHolder(holder: ActionHolder, position: Int) {
        // set category title
        holder.getActionName().text = actions[position].label

        // click listener on user row
        holder.getActionName().setOnClickListener {
            val intent = activity.intent
            intent.putExtra(ListCategoryActivity.ACTION_NAME, actions[position].label)
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
    }
}