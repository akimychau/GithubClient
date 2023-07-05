package ru.akimychev.githubclient.mvp.model.entity.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "repos",
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubUserRepos(
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    var userId: String? = null
)