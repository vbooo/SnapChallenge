package mypromotion.vboo.com.snapchallenge

import mypromotion.vboo.com.snapchallenge.activity.RankingActivity
import mypromotion.vboo.com.snapchallenge.model.HeaderRankingInfo

interface RankingListDataSource {
    /** The number of item present in the dataSource */
    val nbItem: Int

    /** Check whether there is a header at the given position */
    fun isHeaderAt(index: Int): Boolean

    fun getHeaderAt(index: Int): HeaderRankingInfo

    /** Check whether there is a control at the given position */
    fun isUserAt(index: Int): Boolean

    fun getUserAt(index: Int): RankingActivity.User

    /** Reset the data, re-loading it from the database on the next access. */
    fun reset()
}