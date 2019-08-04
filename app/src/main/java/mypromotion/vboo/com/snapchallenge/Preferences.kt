package mypromotion.vboo.com.snapchallenge

import android.content.Context
import android.content.SharedPreferences

class Preferences {

    companion object {
        val PREFS_DIR = "Session"
        val PREFS_KEY_ACCESS_TOKEN = "accessToken"

        //get singleton for preferences
        fun get(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREFS_DIR, Context.MODE_PRIVATE)
        }

        fun getEditor(context: Context): SharedPreferences.Editor {
            return get(context).edit()
        }

        fun getToken(context: Context): String? {
            return get(context).getString(PREFS_KEY_ACCESS_TOKEN, null)
        }

        fun setToken(context: Context, token: String) {
            getEditor(context).putString(PREFS_KEY_ACCESS_TOKEN, token)
        }
    }

}