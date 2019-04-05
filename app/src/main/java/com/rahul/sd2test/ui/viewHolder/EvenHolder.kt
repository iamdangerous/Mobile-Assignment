package com.rahul.sd2test.ui.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.rahul.sd2test.R
import com.rahul.sd2test.ui.adapter.ImagesAdapter
import java.util.*

class EvenHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val rv: RecyclerView
    val imageViewSmall: CircularImageView
    val imageViewBig: AppCompatImageView
    val tvTitle: AppCompatTextView

    val imagesList = ArrayList<String>()
    val imagesAdapter: ImagesAdapter

    init {
        rv = itemView.findViewById(R.id.rv)
        imageViewSmall = itemView.findViewById(R.id.small_image_view)
        imageViewBig = itemView.findViewById(R.id.big_image_view)
        tvTitle = itemView.findViewById(R.id.tv)

        imagesAdapter = ImagesAdapter(itemView.context,imagesList)

        val gridLayoutManager = GridLayoutManager(itemView.context, 2)
        rv.layoutManager = gridLayoutManager
        rv.adapter = imagesAdapter
    }

    fun init(images: ArrayList<String>) {
        imagesList.clear()
        imagesList.addAll(images)
        imagesAdapter.notifyItemRangeInserted(0, imagesList.size)
    }
}