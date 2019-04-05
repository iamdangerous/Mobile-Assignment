package com.rahul.sd2test.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.sd2test.R
import com.rahul.sd2test.extras.Constants
import com.rahul.sd2test.ui.viewHolder.ImagesHolder
import java.util.*

class ImagesAdapter(private val context: Context, internal var arrayList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_images, parent, false)
        return ImagesHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val imagesHolder = holder as ImagesHolder
        val url = arrayList[position]

        Glide.with(context)
            .load(url)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder))
            .into(imagesHolder.imageView)

        val rightPadding = Constants.convertDpToPixel(6f, context).toInt()
        val leftPadding = Constants.convertDpToPixel(1f, context).toInt()
        val topPadding = Constants.convertDpToPixel(5f, context).toInt()

        if (position % 2 == 0) {
            imagesHolder.fm.setPadding(leftPadding, topPadding, rightPadding, 0)
        } else {
            imagesHolder.fm.setPadding(rightPadding, topPadding, leftPadding, 0)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
