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
    var userItems = ArrayList<UserData>()
    var userRepos = ArrayList<Repo>()
    lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(userData: UserData)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }

    fun setDataList(data: ArrayList<UserData>) {
        this.userItems = data
    }

    fun setRepoList(repo: ArrayList<Repo>){
        this.userRepos = repo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = com.example.github.databinding.RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = userItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userItems[position])
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                myListener.onItemClick(userItems[holder.getAdapterPosition()])
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
        fun loadImage(userIcon: ImageView, url: String) {
            Glide.with(userIcon)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(userIcon)
        }

    }

}