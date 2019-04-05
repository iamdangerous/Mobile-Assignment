package com.rahul.sd2test.ui.viewHolder

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.sd2test.R

class ImagesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: AppCompatImageView
    val fm: FrameLayout

    init {
        imageView = itemView.findViewById(R.id.image_view)
        fm = itemView.findViewById(R.id.fm)
    }
}