package com.example.usergit.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usergit.domain.UserEntity

class AdapterUsers : RecyclerView.Adapter<ViewHolderUser>() {

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
        ViewHolderUser(parent)


    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = usersDataList.size

    private fun getItem(pos: Int) = usersDataList[pos]
}