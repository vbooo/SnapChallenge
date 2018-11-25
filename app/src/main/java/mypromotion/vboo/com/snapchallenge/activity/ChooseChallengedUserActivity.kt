package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_choose_challenged_user.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.ChallengedUserAdapter
import mypromotion.vboo.com.snapchallenge.model.User
import java.util.*

class ChooseChallengedUserActivity : AppCompatActivity() {

    private var idChallengedUser: Int? = null
    private var context: Context? = null

    companion object {
        const val ID_CHALLENGED_USER = "idChalengedUser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_challenged_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        context = this

        // get the selected challenged user id if exists
        idChallengedUser = intent.getIntExtra(ID_CHALLENGED_USER, 0)

        initRecyclerView()
    }

    /**
     * Users recyclerview initialization
     */
    private fun initRecyclerView() {
        activity_choose_challenged_user_list.setHasFixedSize(true)
        activity_choose_challenged_user_list.layoutManager = LinearLayoutManager(context)
        activity_choose_challenged_user_list.adapter = ChallengedUserAdapter(this, getFriends(), idChallengedUser)
    }

    /**
     * Save the selected user to the activity
     */
    fun saveChallengedUser(idUserToSave: Int?) {
        val intent = intent
        intent.putExtra(ID_CHALLENGED_USER, idUserToSave)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getFriends(): List<User> {
        val listFriends = arrayListOf<User>()
        listFriends.clear()
        listFriends.add(User(1, "Francis", Date(), "", 2867, 23, 34))
        listFriends.add(User(2, "Bruno", Date(), "", 2867, 23, 34))
        listFriends.add(User(3, "Romain", Date(), "", 2867, 23, 34))
        listFriends.add(User(4, "Nicolas", Date(), "", 2867, 23, 34))
        listFriends.add(User(5, "Yenny", Date(), "", 2867, 23, 34))
        listFriends.add(User(6, "Julien", Date(), "", 2867, 23, 34))
        listFriends.add(User(7,"Nathalie", Date(), "", 2867, 23, 34))
        listFriends.add(User(8, "Helene", Date(), "", 2867, 23, 34))
        listFriends.add(User(9, "Romain", Date(), "", 2867, 23, 34))
        listFriends.add(User(10, "Souleyman", Date(), "", 2867, 23, 34))
        listFriends.add(User(11, "Pierre", Date(), "", 2867, 23, 34))
        listFriends.add(User(12, "Marc", Date(), "", 2867, 23, 34))
        listFriends.add(User(13, "Joris", Date(), "", 2867, 23, 34))
        listFriends.add(User(14, "Michael", Date(), "", 2867, 23, 34))
        listFriends.add(User(15, "Kevin", Date(), "", 2867, 23, 34))
        return listFriends
    }
}
