package mypromotion.vboo.com.snapchallenge.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.animation.TranslateAnimation
import android.widget.SeekBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lay_down_challenge.*
import kotlinx.android.synthetic.main.choose_challenge_time_dialog.*
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.model.Challenge
import mypromotion.vboo.com.snapchallenge.viewModel.LayDownChallengeViewModel


class LayDownChallengeActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var viewModel: LayDownChallengeViewModel
    private val REQUEST_CODE_ADD_CHALLENGED_USER = 1
    private val REQUEST_CODE_ADD_NEW_ACTION = 2
    private val REQUEST_CODE_GET_ACTION_IN_LIST = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lay_down_challenge)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = LayDownChallengeViewModel(Challenge(), this)
        context = this


        Picasso.get().load(R.drawable.picture_fabien).error(R.drawable.user_default).placeholder(R.drawable.user_default)
        .into(activity_lay_down_challenge_author_picture)

        val animationFromBottom350 = TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationFromBottom350.duration = 370 // animation duration
        animationFromBottom350.fillAfter = true
        activity_lay_down_challenge_author_picture.startAnimation(animationFromBottom350)

        Picasso.get().load(R.drawable.user_default).error(R.drawable.user_default).placeholder(R.drawable.user_default)
                .into(activity_lay_down_challenge_challenged_user_picture)

        activity_lay_down_challenge_challenged_user_picture.startAnimation(animationFromBottom350)

        handleClickUserChallenged()
        handleAddAction()
        handleTime()

        activity_lay_down_challenge_time_value.text = viewModel.getTimeValue(0)
        //activity_lay_down_challenge_action_background.visibility = viewModel.actionImageBackgroundVisibility()


    }

    private fun handleTime() {
        activity_lay_down_challenge_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                activity_lay_down_challenge_time_value.text = viewModel.getTimeValue(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    private fun handleClickUserChallenged() {
        activity_lay_down_challenge_challenged_user_picture.setOnClickListener {
            val intent = Intent(context, ChooseChallengedUserActivity::class.java)
            intent.putExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, viewModel.challenge.idChallengedUser)
            startActivityForResult(intent, REQUEST_CODE_ADD_CHALLENGED_USER)
        }
    }

    private fun handleTimeAction() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.choose_challenge_time_dialog)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    //handleSetChallengeTime()
                }
                .setNegativeButton(R.string.no_time) { _, _ ->
                    viewModel.tempDay = 0
                    viewModel.tempHour = 0
                    viewModel.tempMinute = 0
                }
        val dialog = builder.create()
        dialog.show()

        dialog.choose_challenge_time_dialog_picker_day.maxValue = 364
        dialog.choose_challenge_time_dialog_picker_day.minValue = 0
        dialog.choose_challenge_time_dialog_picker_day.value = viewModel.tempDay


        dialog.choose_challenge_time_dialog_picker_hour.maxValue = 23
        dialog.choose_challenge_time_dialog_picker_hour.minValue = 0
        dialog.choose_challenge_time_dialog_picker_hour.value = viewModel.tempHour

        dialog.choose_challenge_time_dialog_picker_minute.maxValue = 59
        dialog.choose_challenge_time_dialog_picker_minute.minValue = 0
        dialog.choose_challenge_time_dialog_picker_minute.value = viewModel.tempMinute


        dialog.choose_challenge_time_dialog_picker_day.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempDay = newVal
        }

        dialog.choose_challenge_time_dialog_picker_hour.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempHour = newVal
        }

        dialog.choose_challenge_time_dialog_picker_minute.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.tempMinute = newVal
        }
    }

    private fun handleAddAction() {
        activity_lay_down_challenge_action_layout.setOnClickListener {
            val intent = Intent(context, ListCategoryActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_GET_ACTION_IN_LIST)
        }
    }

    /**
     * Used for get the challenged user chosen in ChooseChallengedUserActivity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ADD_CHALLENGED_USER && resultCode == Activity.RESULT_OK) {
            onChallengedUserChosen(data?.getIntExtra(ChooseChallengedUserActivity.ID_CHALLENGED_USER, 0))
        } else if (requestCode == REQUEST_CODE_ADD_NEW_ACTION && resultCode == Activity.RESULT_OK) {
            onActionChosen(data?.getStringExtra(NewActionActivity.ACTION_NAME))
        } else if (requestCode == REQUEST_CODE_GET_ACTION_IN_LIST && resultCode == Activity.RESULT_OK) {
            onActionChosen(data?.getStringExtra(ListCategoryActivity.ACTION_NAME))
        }
        super.onActivityResult(requestCode, resultCode, data)
        updateLayDownChallenge()
    }

    private fun onActionChosen(stringExtra: String?) {
        viewModel.challenge.tempActionName = stringExtra
        activity_lay_down_challenge_picture_swords.visibility = viewModel.iconActionVisibility()
        activity_lay_down_challenge_action_name.text = viewModel.getActionName()

        //activity_lay_down_challenge_action_layout.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        //activity_lay_down_challenge_action_background.visibility = viewModel.actionImageBackgroundVisibility()
        //activity_lay_down_challenge_action_background.setImageResource(R.drawable.sport_action)

        val bitImg = BitmapFactory.decodeResource(resources,
                R.drawable.sport_action)
        //activity_lay_down_challenge_action_background.setImageBitmap(getRoundedCornerImage(bitImg))
        //activity_lay_down_challenge_action_background.setColorFilter(0xffa6a6a6.toInt(), PorterDuff.Mode.MULTIPLY )
    }

    fun getRoundedCornerImage(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width,
                bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = 100f

        paint.setAntiAlias(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(color)
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output

    }

    private fun handleLayDownButton() {

    }

    private fun updateLayDownChallenge() {
        activity_lay_down_challenge_lay_down.setTextColor(viewModel.getLayDownChallengeTextColor())
        activity_lay_down_challenge_layout_lay_down.isClickable = viewModel.isLayDownChallengeLayoutClickable()
        if (activity_lay_down_challenge_layout_lay_down.isClickable) {
            activity_lay_down_challenge_layout_lay_down.setOnClickListener {
                finish()
            }
        }
    }

    /**
     * Called when a challenged user has been chosen in the list
     */
    private fun onChallengedUserChosen(idChallengedUser: Int?) {
        viewModel.challenge.idChallengedUser = idChallengedUser

    }

    private fun discardChallengeCreation() {
        val alertDiscardChallenge = AlertDialog.Builder(this).create()
        // set the message
        alertDiscardChallenge.setMessage(resources.getString(R.string.discardChallengeCreation))

        // set the AlertDialog buttons
        alertDiscardChallenge.setButton(AlertDialog.BUTTON_NEGATIVE, resources.getString(android.R.string.cancel)) {
            _, _ ->
        }
        alertDiscardChallenge.setButton(AlertDialog.BUTTON_POSITIVE, resources.getString(android.R.string.yes)) {
            _, _ -> finish()

        }
        alertDiscardChallenge.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            discardChallengeCreation()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        discardChallengeCreation()
        return true
    }
}
