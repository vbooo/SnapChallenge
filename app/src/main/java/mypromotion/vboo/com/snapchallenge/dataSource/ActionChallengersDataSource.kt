package mypromotion.vboo.com.snapchallenge.dataSource

import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.HeaderActionChallengers

interface ActionChallengersDataSource {
    /** The number of item present in the dataSource */
    val nbItem: Int

    /** Check whether there is a header at the given position */
    fun isHeaderAt(index: Int): Boolean

    fun getHeaderAt(index: Int): HeaderActionChallengers

    /** Check whether there is a control at the given position */
    fun isChallengeUserAt(index: Int): Boolean

    fun getChallengeUserAt(index: Int): ChallengeAnswer

    /** Reset the data, re-loading it from the database on the next access. */
    fun reset()
}