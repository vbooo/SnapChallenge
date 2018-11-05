package mypromotion.vboo.com.snapchallenge.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckedTextView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_user.view.*

class UserHolder(var view: View) : RecyclerView.ViewHolder(view) {

    /**
     * @return a user row
     */
    fun getUserRow(): CheckedTextView {
        return view.item_user_text_view
    }

    fun getUserLayout(): RelativeLayout {
        return view.checklist_layout
    }
}