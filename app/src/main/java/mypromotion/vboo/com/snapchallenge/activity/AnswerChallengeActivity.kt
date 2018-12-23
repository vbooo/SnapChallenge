package mypromotion.vboo.com.snapchallenge.activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_answer_challenge.*
import kotlinx.android.synthetic.main.activity_privacy_challenge.*
import kotlinx.android.synthetic.main.fab_action_answer_challenge.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.viewModel.AnswerChallengeViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AnswerChallengeActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_answer_challenge)

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

        handleHeader()

        /*activity_publish_challenge_image_camera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            dispatchTakePictureIntent()
        }

        activity_publish_challenge_video.setOnClickListener {
            val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            //dispatchTakePictureIntent(takeVideoIntent)
        }*/


    }

    private fun handleHeader() {
        activity_answer_challenge_second_line.visibility = viewModel.visibilitySecondLineHeader()
        activity_answer_challenge_action_name.text = viewModel.nameAction
        Picasso.get().load(viewModel.urlAuthor).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(activity_answer_challenge_author_picture)
        Picasso.get().load(viewModel.challengedUser).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(activity_answer_challenge_challenged_user_picture)

        updatePrivacyButton()
        activity_answer_challenge_privacy_layout.setOnClickListener {
            displayPrivacyDialog()
        }
    }

    private fun updatePrivacyButton() {
        activity_answer_challenge_privacy.text = viewModel.privacyValue()
        activity_answer_challenge_privacy_layout.setBackgroundResource(viewModel.getBackgroundPrivacy())
    }

    private fun displayPrivacyDialog() {
        val alertDiscardChallenge = AlertDialog.Builder(this).create()
        val customViewPrivacy = LayoutInflater.from(this).inflate(R.layout.activity_privacy_challenge, null)
        alertDiscardChallenge.setView(customViewPrivacy)

        alertDiscardChallenge.show()

        alertDiscardChallenge.activity_privacy_challenge_checkbox_private.isChecked = viewModel.privateCheckboxIsChecked()
        alertDiscardChallenge.activity_privacy_challenge_checkbox_public.isChecked = viewModel.publicCheckboxIsChecked()


        alertDiscardChallenge.activity_privacy_challenge_checkbox_public.setOnCheckedChangeListener{ _, isChecked ->
            if (!viewModel.answerIsPublic) {
                viewModel.answerIsPublic = isChecked
                alertDiscardChallenge.activity_privacy_challenge_checkbox_private.isChecked = viewModel.privateCheckboxIsChecked()
                alertDiscardChallenge.activity_privacy_challenge_checkbox_public.isChecked = viewModel.publicCheckboxIsChecked()
            }
            updatePrivacyButton()
            alertDiscardChallenge.dismiss()
        }

        alertDiscardChallenge.activity_privacy_challenge_checkbox_private.setOnCheckedChangeListener{ _, isChecked ->
            if (viewModel.answerIsPublic) {
                viewModel.answerIsPublic = !isChecked
                alertDiscardChallenge.activity_privacy_challenge_checkbox_private.isChecked = viewModel.privateCheckboxIsChecked()
                alertDiscardChallenge.activity_privacy_challenge_checkbox_public.isChecked = viewModel.publicCheckboxIsChecked()
            }
            updatePrivacyButton()
            alertDiscardChallenge.dismiss()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                            this,
                            "mypromotion.vboo.com.snapchallenge.fileprovider",
                            it
                    )
                    //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
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
            //activity_answer_challenge_image.setImageBitmap(bitmap)
        }

        val bitmap =  BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
        //activity_answer_challenge_image.setImageBitmap(bitmap)
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
