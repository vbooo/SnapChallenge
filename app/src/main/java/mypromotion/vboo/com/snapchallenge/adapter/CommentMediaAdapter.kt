package mypromotion.vboo.com.snapchallenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mypromotion.vboo.com.snapchallenge.R
import mypromotion.vboo.com.snapchallenge.holder.CommentMediaHolder
import mypromotion.vboo.com.snapchallenge.model.CommentMedia


class CommentMediaAdapter(private var dataSet: MutableList<CommentMedia>, var context: Context) : RecyclerView.Adapter<CommentMediaHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentMediaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentMediaHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CommentMediaHolder, position: Int) {
        holder.setUserPicture(dataSet[position].urlAuthor)
        holder.setUserName(dataSet[position].nameAuthor)
        holder.setDateComment(dataSet[position].dateComment)
        holder.setComment(dataSet[position].comment)
        val like = dataSet[position].nbLike.toString() + " " + context.resources.getString(R.string.likeWord)
        holder.setNbLike(like)

    }
}