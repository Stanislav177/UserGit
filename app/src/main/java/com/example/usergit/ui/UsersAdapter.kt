package com.example.usergit.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usergit.domain.UserEntity

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var usersDataList = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    fun setUsersDataList(data: List<UserEntity>) {
        usersDataList.clear()
        usersDataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent)


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = usersDataList.size

    private fun getItem(pos: Int) = usersDataList[pos]
}