package com.example.usergit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.usergit.R
import com.example.usergit.domain.UserEntity
import com.example.usergit.databinding.ItemUserGitBinding

class ViewHolderUser(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_user_git, parent, false)) {

    private val binding = ItemUserGitBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        with(binding) {
            avatarUser.load(userEntity.urlAvatar)
            idUser.text = userEntity.id.toString()
            nickNameUser.text = userEntity.nickname
        }
    }
}