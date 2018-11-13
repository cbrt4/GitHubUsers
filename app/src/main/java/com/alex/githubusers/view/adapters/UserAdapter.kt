package com.alex.githubusers.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.githubusers.R
import com.alex.githubusers.listeners.OnItemClickListener
import com.alex.githubusers.model.UserEntity
import com.alex.githubusers.util.GlideApp
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_user_recycler.view.*
import javax.inject.Inject

class UserAdapter  @Inject constructor(private val appContext: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var users = ArrayList<UserEntity>()
    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(users[holder.adapterPosition])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(userEntity: UserEntity) {
            itemView.userName.text = userEntity.login
            itemView.userId.text = String.format(appContext.getString(R.string.id), userEntity.id)

            GlideApp.with(appContext)
                .load(userEntity.avatarUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .into(itemView.userAvatar)

            itemView.setOnClickListener{ listener?.onItemClick(adapterPosition) }
        }
    }
}