package ru.akimychev.githubclient.mvp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.akimychev.githubclient.mvp.model.entity.RoomGithubUser

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
    val forksCount: Int,
    var userId: String? = null
)