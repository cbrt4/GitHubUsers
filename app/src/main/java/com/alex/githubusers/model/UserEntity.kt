package com.alex.githubusers.model

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
