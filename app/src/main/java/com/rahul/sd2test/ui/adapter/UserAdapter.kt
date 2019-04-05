package com.rahul.sd2test.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.sd2test.R
import com.rahul.sd2test.modal.User
import com.rahul.sd2test.ui.viewHolder.EvenHolder
import com.rahul.sd2test.ui.viewHolder.LoadMoreHolder
import java.util.*

class UserAdapter(private val context: Context, internal var arrayList: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_NORMAL = 2
    private val TYPE_LOAD_MORE = 3
    private var iUserAdapter: IUserAdapter? = null

    fun setCallback(iUserAdapter: IUserAdapter) {
        this.iUserAdapter = iUserAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_NORMAL) {
            val v = LayoutInflater.from(context).inflate(R.layout.item_even, parent, false)
            return EvenHolder(v)
        } else {
            val v = LayoutInflater.from(context).inflate(R.layout.item_load_more, parent, false)
            return LoadMoreHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder.itemViewType == TYPE_NORMAL) {
            val evenHolder = holder as EvenHolder
            val user = arrayList[position]
            val images = user.items

            evenHolder.tvTitle.text = user.name

            if (images.size % 2 == 0) {
                evenHolder.init(images)
                evenHolder.imageViewBig.visibility = View.GONE

            } else {
                if (images.size != 1) {
                    images.removeAt(0)
                    evenHolder.init(images)
                }
                evenHolder.imageViewBig.visibility = View.VISIBLE
                Glide.with(evenHolder.imageViewBig)
                    .load(user.items[0])
                    .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder))
                    .into(evenHolder.imageViewBig)

            }
            Glide.with(evenHolder.imageViewSmall)
                .load(user.image)
                .placeholder(ContextCompat.getDrawable(context, R.drawable.circle_placeholder))
                .into(evenHolder.imageViewSmall)

            if (position == arrayList.size - 4 && arrayList.size > 6 && iUserAdapter != null) {
                iUserAdapter!!.loadMore()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (arrayList[position].loadMore)
            TYPE_LOAD_MORE
        else
            TYPE_NORMAL

    }

    interface IUserAdapter {
        fun loadMore()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
