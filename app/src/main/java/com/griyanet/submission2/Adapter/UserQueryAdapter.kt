package com.griyanet.submission2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.griyanet.submission2.Model.Item
import com.griyanet.submission2.R
import kotlinx.android.synthetic.main.row_itemuser.view.*

class UserQueryAdapter : RecyclerView.Adapter<UserQueryAdapter.UserViewHolder>() {

    private var userQueryList = ArrayList<Item>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun userBind(item: Item) {
            with(itemView) {
                tv_login.text = item.login
                tv_userUrl.text = item.url
                tv_followerUrl.text = item.followers_url
                tv_followingUrl.text = item.following_url
                Glide.with(itemView.iv_avatar)
                        .load(item.avatar_url)
                        .into(iv_avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_itemuser, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userBind(userQueryList[position])
    }

    override fun getItemCount(): Int = userQueryList.size

    fun setData(newList: List<Item>) {
        userQueryList = newList as ArrayList<Item>
        notifyDataSetChanged()
    }

}