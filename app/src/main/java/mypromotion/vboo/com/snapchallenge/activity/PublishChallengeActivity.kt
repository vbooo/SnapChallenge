package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_publish_challenge.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.viewModel.PublishChallengeViewModel
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
    private lateinit var context: Context

    private var isAnswerToSomeone: Boolean = false
    private var nameAction: String? = null
    private var urlAuthor: String? = null
    private var urlUser = "https://randomuser.me/api/portraits/thumb/men/43.jpg"

    private lateinit var viewModel: PublishChallengeViewModel

    companion object {
        const val NAME_ACTION = "name_action"
        const val IS_ANSWER_TO_SOMEONE = "is_answer_to_someone"
        const val URL_AUTHOR = "url_author"
        private val REQUEST_CODE_GET_ACTION_IN_LIST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_challenge)

        context = this

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

        viewModel = PublishChallengeViewModel(this, nameAction, isAnswerToSomeone, urlAuthor, urlUser, true)

        handleAddAction()

    }

    private fun handleAddAction() {
        activity_publish_challenge_action_big_layout.setOnClickListener {
            val intent = Intent(context, ListCategoryActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_GET_ACTION_IN_LIST)
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
            //activity_publish_challenge_image.setImageBitmap(bitmap)
        }

        val bitmap =  BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
                //activity_publish_challenge_image.setImageBitmap(bitmap)
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

        } else if (requestCode == REQUEST_CODE_GET_ACTION_IN_LIST && resultCode == Activity.RESULT_OK) {
            onActionChosen(data?.getStringExtra(ListCategoryActivity.ACTION_NAME))
        }
    }

    private fun onActionChosen(stringExtra: String?) {
        viewModel.nameAction = stringExtra
        activity_publish_challenge_layout_add_action.visibility = viewModel.placeholderAddActionVisibility()
        activity_publish_challenge_action_name.visibility = viewModel.nameActionVisibility()
        activity_publish_challenge_action_name.text = viewModel.nameAction

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
