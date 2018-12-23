package mypromotion.vboo.com.snapchallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ChooseChallengedUserActivity
import mypromotion.vboo.com.snapchallenge.holder.UserHolder
import mypromotion.vboo.com.snapchallenge.model.User

class ChallengedUserAdapter (private val activity: ChooseChallengedUserActivity, private val users: List<User>, private val idSelectedUser : Int?) : RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        // set user title
        holder.getUserRow().text = users[position].name

        // check the selected user
        holder.getUserRow().isChecked = users[position].id == idSelectedUser

        // click listener on user row
        holder.getUserLayout().setOnClickListener {
            activity.saveChallengedUser(users[position].id)
        }
    }
}