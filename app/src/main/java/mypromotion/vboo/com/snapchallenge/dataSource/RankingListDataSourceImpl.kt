package mypromotion.vboo.com.snapchallenge.dataSource

import mypromotion.vboo.com.snapchallenge.activity.RankingActivity
import mypromotion.vboo.com.snapchallenge.model.HeaderRankingInfo

class RankingListDataSourceImpl(var listUser: List<RankingActivity.User>) : RankingListDataSource {

    private var items: List<Any>? = null
        get() {
            if (field == null) {
                field = this.generateOrganizedItems()
            }
            return field
        }

    /** Generate and organize the data */
    private fun generateOrganizedItems(): List<Any> {
        if (listUser != null) {
            val listItems = mutableListOf<Any>()
            val headerRankingInfo = HeaderRankingInfo(listUser[0].urlPic, listUser[0].name, listUser[0].point,
                    listUser[1].urlPic, listUser[1].name, listUser[1].point,
                    listUser[2].urlPic, listUser[2].name, listUser[2].point)
            listItems.add(headerRankingInfo)
            listUser.forEachIndexed { index, element ->
                if (index > 2) {
                    element.rank = index + 1
                    listItems.add(element)
                }
            }
        return listItems
        }
        return mutableListOf()
    }

    override val nbItem: Int
        get() = items?.size ?: 0

    override fun getHeaderAt(index: Int): HeaderRankingInfo {
        return items?.get(index) as HeaderRankingInfo
    }

    override fun isHeaderAt(index: Int): Boolean {
        return items?.get(index) is HeaderRankingInfo
    }

    override fun isUserAt(index: Int): Boolean {
        return items?.get(index) is RankingActivity.User
    }

    override fun getUserAt(index: Int): RankingActivity.User {
        return items?.get(index) as RankingActivity.User
    }

    override fun reset() {
        items = null
    }

}