package mypromotion.vboo.com.snapchallenge.model

import mypromotion.vboo.com.snapchallenge.R

/**
 * Created by fabie on 17/09/2018.
 */
enum class TypeChallange(var id: Int) {

    SIMPLE(R.string.simple),
    GROUPED(R.string.grouped);

    override fun toString(): String{
        return "TypeChallange(id=$id)"
    }
}