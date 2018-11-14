package mypromotion.vboo.com.snapchallenge

import android.content.Context

enum class AnswerChallengeType(private val resId: Int) {
    unknown(-1),
    photo(R.string.picture),
    video(R.string.video);

    /** Resource string representing the current status */
    fun string(context: Context): String {
        return if (resId != -1) {
            context.getString(resId)
        } else {
            ""
        }
    }

    companion object {

        /** Get a PermitStatus based on the given index */
        fun fromIndex(index : Int): AnswerChallengeType {
            return when(index) {
                1 -> photo
                2 -> video
                else -> unknown
            }
        }

    }
}