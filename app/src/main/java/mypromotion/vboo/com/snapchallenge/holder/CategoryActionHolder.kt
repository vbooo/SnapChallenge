package mypromotion.vboo.com.snapchallenge.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_category_action.view.*

class CategoryActionHolder(var view: View) : RecyclerView.ViewHolder(view) {

    /**
     * @return the category title
     */
    fun getCategoryName(): TextView {
        return view.item_category_name
    }

    fun getCategoryNbItem(): TextView {
        return view.item_category_nb_challenges
    }

    fun getView(): RelativeLayout {
        return view.item_category_view
    }

    fun getImage(): ImageView {
        return view.item_category_image
    }
}