package ru.akimychev.githubclient.mvp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class RoomGithubUser(
    @PrimaryKey
    val id: String,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?
)