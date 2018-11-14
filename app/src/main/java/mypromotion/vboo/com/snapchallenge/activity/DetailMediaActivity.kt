package mypromotion.vboo.com.snapchallenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_media.*
import mypromotion.vboo.com.snapchallenge.R

class DetailMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_media)

        if (true) {
            activity_detail_media_container_video.visibility = View.GONE
            activity_detail_media_container_image.visibility = View.VISIBLE
            Picasso.get().load("https://randomuser.me/api/portraits/women/12.jpg").error(R.drawable.user_default).placeholder(R.drawable.user_default)
                    .into(activity_detail_media_container_image)
        } else {
            activity_detail_media_container_video.visibility = View.VISIBLE
            activity_detail_media_container_image.visibility = View.GONE
        }
    }
}
