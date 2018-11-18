package mypromotion.vboo.com.snapchallenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_comment_media.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.adapter.CommentMediaAdapter
import mypromotion.vboo.com.snapchallenge.model.CommentMedia
import java.util.*

class CommentMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_media)

        val listChallenge = arrayListOf<CommentMedia>()
        listChallenge.clear()
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/men/65.jpg", "Nicolas", Date(), "Whaou ton challenge est ouf, bravo !! Par contre est ce que tu peux le refaire car j'adore et commentaire hyper long", 2))
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/men/64.jpg", "Fabien Bouvet", Date(), "nul", 32))
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/women/65.jpg", "Marie Dupond", Date(), "Pas mal ;)", 0))
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/men/62.jpg", "Julien Fornerod", Date(), "hahaha", 0))
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/men/61.jpg", "Bruno Festin", Date(), "J'adore !!", 1))
        listChallenge.add(CommentMedia(1, "https://randomuser.me/api/portraits/thumb/women/60.jpg", "Valerie Martin", Date(), "Bravo, continue comme ça.", 0))

        val viewAdapter = CommentMediaAdapter(listChallenge, this)

        // apply the manager and the adapter to the permit_list
        activity_comment_media_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = viewAdapter
        }
    }
}
