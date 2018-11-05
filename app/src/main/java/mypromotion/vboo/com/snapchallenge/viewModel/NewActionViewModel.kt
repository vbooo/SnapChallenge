package mypromotion.vboo.com.snapchallenge.viewModel

import android.content.Context
import android.support.v4.content.ContextCompat
import mypromotion.vboo.com.snapchallenge.R

class NewActionViewModel(val context: Context, var actionName: String?) {

    fun getValidateTextColor(): Int {
        return if (actionName == null || actionName!!.isEmpty()) {
            ContextCompat.getColor(context, R.color.blue_theme_opacity)
        } else {
            ContextCompat.getColor(context, R.color.blue_theme)
        }
    }

    fun isLayDownChallengeLayoutClickable(): Boolean {
        return actionName != null && !actionName!!.isEmpty()
    }
}