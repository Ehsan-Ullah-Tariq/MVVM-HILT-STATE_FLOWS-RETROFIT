package com.android.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.test.R
import com.android.test.databinding.RvProductItemBinding
import com.android.test.listeners.ItemsListener
import com.android.test.network.models.HomeDataModelItem
import com.bumptech.glide.Glide

class ItemsAdapter(private val listener: ItemsListener, private var context: Context) :
    ListAdapter<HomeDataModelItem, ItemsAdapter.CategoriesViewHolder>(Companion) {

    inner class CategoriesViewHolder(val itemBinding: RvProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    companion object : DiffUtil.ItemCallback<HomeDataModelItem>() {
        override fun areItemsTheSame(
            oldItem: HomeDataModelItem,
            newItem: HomeDataModelItem
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: HomeDataModelItem,
            newItem: HomeDataModelItem
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        return CategoriesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_product_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)

        if (item.type?.equals("Article") == true) {

            holder.itemBinding.itemTitle.text = item.titile

        } else {
            holder.itemBinding.itemTitle.text = item.name

        }

        Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/insta-clone-11076.appspot.com/o/kotlin.webp?alt=media&token=9d894815-9e30-42fe-867d-10522d5d7741").into(holder.itemBinding.itemImage)

        holder.itemBinding.itemRoot.setOnClickListener {
            listener.onProductClick(position, item)
        }

        holder.itemBinding.executePendingBindings()

    }

}