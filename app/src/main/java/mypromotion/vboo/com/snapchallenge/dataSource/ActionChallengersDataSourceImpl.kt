package mypromotion.vboo.com.snapchallenge.dataSource

import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.HeaderActionChallengers

class ActionChallengersDataSourceImpl(var listChallengeUser: List<ChallengeAnswer>) : ActionChallengersDataSource {

    private var items: List<Any>? = null
        get() {
            if (field == null) {
                field = this.generateOrganizedItems()
            }
            return field
        }

    /** Generate and organize the data */
    private fun generateOrganizedItems(): List<Any> {

            val listItems = mutableListOf<Any>()
            val headerActionChallengers = HeaderActionChallengers(listChallengeUser[0].description, listChallengeUser.size)
            listItems.add(headerActionChallengers)
            listChallengeUser.forEachIndexed { _, element ->
                listItems.add(element)
            }
        return listItems
    }

    override val nbItem: Int
        get() = items?.size ?: 0

    override fun getHeaderAt(index: Int): HeaderActionChallengers {
        return items?.get(index) as HeaderActionChallengers
    }

    override fun isHeaderAt(index: Int): Boolean {
        return items?.get(index) is HeaderActionChallengers
    }

    override fun isChallengeUserAt(index: Int): Boolean {
        return items?.get(index) is ChallengeAnswer
    }

    override fun getChallengeUserAt(index: Int): ChallengeAnswer {
        return items?.get(index) as ChallengeAnswer
    }

    override fun reset() {
        items = null
    }

}