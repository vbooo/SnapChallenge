package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ActionByCategoryActivity
import mypromotion.vboo.com.snapchallenge.activity.ListActionActivity
import mypromotion.vboo.com.snapchallenge.holder.CategoryActionHolder
import mypromotion.vboo.com.snapchallenge.model.CategoryTemp

class CategoryActionsAdapter (private val activity: ListActionActivity, private val categories: List<CategoryTemp>) : RecyclerView.Adapter<CategoryActionHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryActionHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_category_action, parent, false)
        return CategoryActionHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryActionHolder?, position: Int) {
        // set category title
        holder?.getCategoryName()?.text = categories[position].categoryName + " - " + categories[position].nbActions

        // click listener on user row
        holder?.getCategoryName()?.setOnClickListener {
            val intent = Intent(activity, ActionByCategoryActivity::class.java)
            //intent.putExtra(CreateNewActionActivity.NAME_ACTION, "")
            activity.startActivityForResult(intent, ListActionActivity.REQUEST_CODE_ACTION_CHOSE)
        }
    }
}