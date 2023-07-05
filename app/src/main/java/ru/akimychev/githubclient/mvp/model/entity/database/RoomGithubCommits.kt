package ru.akimychev.githubclient.mvp.model.entity.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "commits",
    foreignKeys = [ForeignKey(
        entity = RoomGithubUserRepos::class,
        parentColumns = ["id"],
        childColumns = ["reposId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubCommits(
    @PrimaryKey
    val sha: String,
    val commit: String,
    val htmlUrl: String,
    var reposId: String? = null
)