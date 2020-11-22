package com.griyanet.submission2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.griyanet.submission2.R
import com.griyanet.submission2.model.Item
import kotlinx.android.synthetic.main.row_itemuser.view.*

class UserQueryAdapter(
    private val context: Context,
    private var list: List<Item>,
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<UserQueryAdapter.UserViewHolder>() {

    //private var userQueryList = ArrayList<Item>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun userBind(item: Item, listener: (Item) -> Unit) {
            with(itemView) {
                tv_login.text = item.login
                tv_userUrl.text = item.url
                tv_followerUrl.text = item.followers_url
                tv_followingUrl.text = item.following_url
                Glide.with(itemView.iv_avatar)
                    .load(item.avatar_url)
                    .into(iv_avatar)
            }
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_itemuser, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userBind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Item>) {
        list = newList as ArrayList<Item>
        notifyDataSetChanged()
    }

}