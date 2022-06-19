package com.example.usergit.ui.listUsers

import com.example.usergit.domain.UserEntity

interface OnClickListenerUser {
    fun onClick(user: UserEntity)
}