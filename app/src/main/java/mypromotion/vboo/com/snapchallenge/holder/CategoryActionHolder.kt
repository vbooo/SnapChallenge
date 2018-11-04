package mypromotion.vboo.com.snapchallenge.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_category_action.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class CategoryActionHolder(var view: View) : RecyclerView.ViewHolder(view) {

    /**
     * @return the category title
     */
    fun getCategoryName(): TextView {
        return view.item_category_name
    }
}