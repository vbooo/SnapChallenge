package mypromotion.vboo.com.snapchallenge.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_action.view.*
import kotlinx.android.synthetic.main.item_category_action.view.*

class ActionHolder(var view: View) : RecyclerView.ViewHolder(view) {

    /**
     * @return the category title
     */
    fun getActionName(): TextView {
        return view.item_action_name
    }
}