package com.griyanet.submission2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.griyanet.submission2.R
import com.griyanet.submission2.model.FollowersItem
import kotlinx.android.synthetic.main.row_itemuser.view.*

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.MyViewHolder>() {

    private var userList = ArrayList<FollowersItem>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun userBind(userItem: FollowersItem) {
            with(itemView) {
                tv_login.text = userItem.login
                tv_userUrl.text = userItem.url
                tv_followerUrl.text = userItem.followers_url
                tv_followingUrl.text = userItem.following_url
                Glide.with(itemView.iv_avatar)
                    .load(userItem.avatar_url)
                    .into(iv_avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_itemuser, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun setData(newList: ArrayList<FollowersItem>) {
        userList.clear()
        userList = newList
        notifyDataSetChanged()
    }

}