package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.dataSource.RankingListDataSource
import mypromotion.vboo.com.snapchallenge.holder.RankingHeaderHolder
import mypromotion.vboo.com.snapchallenge.holder.RankingHolder

class RankingAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Enum used to distinguish the different type of row in the recycler view */
    enum class ViewType { HEADER, ROW }

    private lateinit var _listRankingDataSource: RankingListDataSource

    var listRankingDataSource: RankingListDataSource? = null
        set(value) {
            if (value != null) {
                _listRankingDataSource = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.HEADER.ordinal) {
            RankingHeaderHolder.create(parent, context)
        } else {
            RankingHolder.create(parent, context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (_listRankingDataSource.isHeaderAt(position)) {
            ViewType.HEADER.ordinal
        } else {
            ViewType.ROW.ordinal
        }
    }

    override fun getItemCount(): Int {
        return _listRankingDataSource.nbItem
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is RankingHeaderHolder) {
            val header = _listRankingDataSource.getHeaderAt(position)
            holder.setFirstUserPicture(header.urlPicFirstUser)
            holder.setSecondUserPicture(header.urlPicSecondUser)
            holder.setThirdUserPicture(header.urlPicThirdUser)

            holder.setFirstUserName(header.nameFirstUser)
            holder.setSecondUserName(header.nameSecondUser)
            holder.setThirdUserName(header.nameThirdUser)

            holder.setFirstUserPoints(header.nbPointsFirstUser)
            holder.setSecondUserPoints(header.nbPointsSecondUser)
            holder.setThirdUserPoints(header.nbPointsThirdUser)

        } else if (holder is RankingHolder) {
            val user = _listRankingDataSource.getUserAt(position)
            holder.setUserName(user.name)
            holder.setUserRank(user.rank.toString())
            holder.setUserPoints(user.point)
            holder.setUserPicture(user.urlPic)
        }
    }
}