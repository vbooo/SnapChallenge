package mypromotion.vboo.com.snapchallenge.dataSource

import mypromotion.vboo.com.snapchallenge.model.ChallengeAnswer
import mypromotion.vboo.com.snapchallenge.model.User

class ProfilDataSourceImpl(var listChallengeUser: List<ChallengeAnswer>, var user: User) : ProfilDataSource {

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
            listItems.add(user)
            listChallengeUser.forEachIndexed { _, element ->
                listItems.add(element)
            }
        return listItems
    }

    override val nbItem: Int
        get() = items?.size ?: 0

    override fun getHeaderAt(index: Int): User {
        return items?.get(index) as User
    }

    override fun isHeaderAt(index: Int): Boolean {
        return items?.get(index) is User
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