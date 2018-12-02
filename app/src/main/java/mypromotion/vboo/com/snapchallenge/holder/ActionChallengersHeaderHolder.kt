package mypromotion.vboo.com.snapchallenge.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_action_challengers.view.*
import mypromotion.vboo.com.snapchallenge.R

class ActionChallengersHeaderHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context): ActionChallengersHeaderHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_action_challengers, parent, false)
            return ActionChallengersHeaderHolder(view, context)
        }
    }

    fun setActionName(value: String?) {
        view.item_action_challengers_action_name.text = value
    }

    fun setNbChallengers(value: Int) {
        view.item_action_challengers_nb_challengers.text = context.resources.getQuantityString(R.plurals.x_challenger, value, value)
    }
}