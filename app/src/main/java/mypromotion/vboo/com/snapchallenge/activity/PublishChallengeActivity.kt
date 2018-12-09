package mypromotion.vboo.com.snapchallenge.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_answer_challenge.*
import kotlinx.android.synthetic.main.fab_action_answer_challenge.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.viewModel.AnswerChallengeViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class PublishChallengeActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_TAKE_PHOTO = 1
    private var mCurrentPhotoPath: String? = null
    private var photoURI: Uri? = null
    private var fileMedia: File? = null

    private var isAnswerToSomeone: Boolean = false
    private var nameAction: String? = null
    private var urlAuthor: String? = null
    private var urlUser = "https://randomuser.me/api/portraits/thumb/men/43.jpg"

    private lateinit var viewModel: AnswerChallengeViewModel

    companion object {
        const val NAME_ACTION = "name_action"
        const val IS_ANSWER_TO_SOMEONE = "is_answer_to_someone"
        const val URL_AUTHOR = "url_author"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_challenge)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nameAction = if (intent.extras != null && intent.hasExtra(NAME_ACTION)) {
            intent.getStringExtra(NAME_ACTION)
        } else {
            null
        }

        isAnswerToSomeone = if (intent.extras != null && intent.hasExtra(IS_ANSWER_TO_SOMEONE)) {
            intent.getBooleanExtra(IS_ANSWER_TO_SOMEONE, false)
        } else {
            false
        }

        urlAuthor = if (intent.extras != null && intent.getStringExtra(URL_AUTHOR) != null) {
            intent.getStringExtra(URL_AUTHOR)
        } else {
            null
        }

        viewModel = AnswerChallengeViewModel(this, nameAction, isAnswerToSomeone, urlAuthor, urlUser, true)

        activity_answer_challenge_placeholder_media_layout.setOnClickListener {
            if (!activity_answer_challenge_fab.isExpanded) {
                val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
                activity_answer_challenge_fab.startAnimation(shake)
            }
        }

    }


    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = 100
        val targetH: Int = 100

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(mCurrentPhotoPath, this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)?.also { bitmap ->
            activity_answer_challenge_image.setImageBitmap(bitmap)
        }

        val bitmap =  BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
        activity_answer_challenge_image.setImageBitmap(bitmap)
    }


    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(mCurrentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val extra = data?.extras

            setPic()

        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
