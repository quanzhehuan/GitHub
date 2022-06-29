package com.example.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.databinding.RecyclerviewRowBinding

class UserViewAdapter: RecyclerView.Adapter<UserViewAdapter.MyViewHolder>() {
    var items = ArrayList<UserData>()
    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(userData: UserData)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    fun setDataList(data :  ArrayList<UserData>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = com.example.github.databinding.RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mListener.onItemClick(items[holder.getAdapterPosition()]) // A modifier
            }
        })
    }

    class MyViewHolder(val binding: RecyclerviewRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserData) {
            binding.userListData = data
            binding.executePendingBindings()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thubmImage: ImageView, url: String) {
            Glide.with(thubmImage)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thubmImage)
        }

    }

}