package com.example.usergit.ui.listUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.usergit.R
import com.example.usergit.databinding.ItemUserGitBinding
import com.example.usergit.domain.UserEntity

class UsersViewHolder(parent: ViewGroup, private val myOnclickUser: OnClickListenerUser) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_user_git, parent, false)) {

    private val binding = ItemUserGitBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        with(binding) {
            avatarUser.load(userEntity.urlAvatar)
            idUser.text = userEntity.id.toString()
            nickNameUser.text = userEntity.login
        }
        itemView.setOnClickListener {
            myOnclickUser.onClick(userEntity.login)
        }
    }

}