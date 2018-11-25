package mypromotion.vboo.com.snapchallenge.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_media.*
import mypromotion.vboo.com.snapchallenge.AnswerChallengeType
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.viewModel.DetailMediaViewModel

class DetailMediaActivity : AppCompatActivity() {

    private lateinit var challenge: ChallengeAnswer
    private lateinit var viewModel: DetailMediaViewModel

    companion object {
        const val CHALLENGE_ANSWER = "challengeAnswer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_media)

        supportActionBar?.hide()

        challenge = intent.getSerializableExtra(CHALLENGE_ANSWER) as ChallengeAnswer

        viewModel = DetailMediaViewModel(challenge)

        activity_detail_media_container_video.visibility = viewModel.videoVisibility()
        activity_detail_media_container_image.visibility = viewModel.pictureVisibility()
        if (viewModel.challengeAnswer.typeChallenge == AnswerChallengeType.photo.ordinal) {
            Picasso.get().load(challenge.urlMedia).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                    .into(activity_detail_media_container_image)
        }

        activity_detail_media_layout_comment.setOnClickListener {
            val intent = Intent(this, CommentMediaActivity::class.java)
            startActivity(intent)
        }
    }
}
