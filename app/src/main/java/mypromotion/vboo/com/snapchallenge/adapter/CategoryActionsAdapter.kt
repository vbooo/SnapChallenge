package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Intent
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.activity.ActionByCategoryActivity
import mypromotion.vboo.com.snapchallenge.activity.ListCategoryActivity
import mypromotion.vboo.com.snapchallenge.holder.CategoryActionHolder
import mypromotion.vboo.com.snapchallenge.model.CategoryTemp

class CategoryActionsAdapter (private val activity: ListCategoryActivity, private val categories: List<CategoryTemp>) : RecyclerView.Adapter<CategoryActionHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryActionHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_category_action, parent, false)
        return CategoryActionHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryActionHolder?, position: Int) {
        // set category title
        holder?.getCategoryName()?.text = categories[position].categoryName
        holder?.getCategoryNbItem()?.text = categories[position].nbActions

        holder?.getImage()?.setColorFilter(0xffbfbfbf.toInt(), PorterDuff.Mode.MULTIPLY )

        when {
            categories[position].categoryName == "Divers" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.divers_300))
            categories[position].categoryName == "Sport" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.sport_300))
            categories[position].categoryName == "Couple" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.couple_300))
            categories[position].categoryName == "Sortie" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.sortie_300))
            categories[position].categoryName == "Voyage" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.voyage_300))
            categories[position].categoryName == "Animaux" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.animals_300))
            categories[position].categoryName == "Vacances" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.holidays_300))
            categories[position].categoryName == "Alimentation" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.food_300))
            categories[position].categoryName == "Maison" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.house_300))
            categories[position].categoryName == "Famille" -> holder?.getImage()?.setImageDrawable(activity.resources.getDrawable(R.drawable.family_300))
        }


        // click listener on user row


        // click listener on user row
        holder?.getView()?.setOnClickListener {
            val intent = Intent(activity, ActionByCategoryActivity::class.java)
            //intent.putExtra(CreateNewActionActivity.NAME_ACTION, "")
            activity.startActivityForResult(intent, ListCategoryActivity.REQUEST_CODE_ACTION_CHOSE)
        }
    }
}