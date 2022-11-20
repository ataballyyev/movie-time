package com.films.movietime.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.films.domain.model.Search
import com.films.movietime.R
import javax.inject.Inject

class MainAdapter @Inject constructor(): RecyclerView.Adapter<MainAdapter.MainVH>() {

    private var listSearchResults: List<Search> = emptyList()
    private var likedList: ArrayList<Boolean> = arrayListOf()
    private lateinit var itemClickListener: OnItemClickListener

    inner class MainVH(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var image: ImageView = itemView.findViewById(R.id.posterImage)
        var title: TextView = itemView.findViewById(R.id.posterTitle)
        var publishedYear: TextView = itemView.findViewById(R.id.movieYear)
        var movieType: TextView = itemView.findViewById(R.id.movieCategory)
        private var likeButton: ImageView = itemView.findViewById(R.id.likeButton)

        init {
            itemView.setOnClickListener(this)
            likeButton.setOnClickListener {
                if (!likedList[adapterPosition]) {
                    likeButton.setImageDrawable(likeButton.context.resources.getDrawable(R.drawable.liked))
                    likedList[adapterPosition] = true
                } else {
                    likeButton.setImageDrawable(likeButton.context.resources.getDrawable(R.drawable.like))
                    likedList[adapterPosition] = false
                }
            }
        }

        override fun onClick(p0: View?) {
            itemClickListener.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_list_item, parent, false)
        return MainVH(view)
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.title.text = listSearchResults[position].Title
        holder.publishedYear.text = listSearchResults[position].Year
        holder.movieType.text = listSearchResults[position].Type
        Glide.with(holder.image)
            .load(listSearchResults[position].Poster)
            .into(holder.image)
    }

    override fun getItemCount() = listSearchResults.size

    fun initializeListSearchResults(list: List<Search>) {
        listSearchResults = list
        repeat(listSearchResults.size) {
            likedList.add(false)
        }
        notifyDataSetChanged()
    }

    fun initializeItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}