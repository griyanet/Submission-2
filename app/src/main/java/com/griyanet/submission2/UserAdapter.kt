package com.griyanet.submission2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.griyanet.submission2.Model.UserItem
import kotlinx.android.synthetic.main.row_itemuser.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var userList = ArrayList<UserItem>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun userBind(userItem: UserItem) {
            with(itemView) {
                tv_login.text = userItem.login
                tv_userUrl.text = userItem.url
                tv_followerUrl.text = userItem.followersUrl
                tv_followingUrl.text = userItem.followingUrl
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

}