package com.example.musicpicture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(
    list: List<Music>,
    click: ClickItemListenner,
    context: Any?
) : RecyclerView.Adapter<MyAdapter.Holder>() {
    private var music: List<Music>? = list
    private var listener: ClickItemListenner? = click
    private var context: Context? = context as Context?

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageRow: ImageView = itemView.findViewById(R.id.icon)
        var titleRow: TextView = itemView.findViewById(R.id.title)
        var authorRow: TextView = itemView.findViewById(R.id.author)
        var timeRow: TextView = itemView.findViewById(R.id.time)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val key: Music = music!![position]
        if (key.getIcon()!!.isNotEmpty()) {
            Picasso.with(context).load(key.getIcon()).into(holder.imageRow)
        }
        holder.titleRow.text = key.getTitle()
        holder.authorRow.text = key.getAuthor()
        holder.timeRow.text = key.getTime().toString()
        holder.itemView.setOnClickListener { listener!!.onItemClick(key) }
    }

    override fun getItemCount(): Int {
        return music!!.size
    }
}