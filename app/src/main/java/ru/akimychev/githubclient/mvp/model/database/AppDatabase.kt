package ru.akimychev.githubclient.mvp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.akimychev.githubclient.mvp.model.database.dao.RoomCommitsDao
import ru.akimychev.githubclient.mvp.model.database.dao.RoomGithubUserDao
import ru.akimychev.githubclient.mvp.model.database.dao.RoomGithubUserReposDao
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubCommits
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubUser
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubUserRepos

@Database(
    entities = [RoomGithubUser::class, RoomGithubUserRepos::class, RoomGithubCommits::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomGithubUserDao
    abstract fun reposDao(): RoomGithubUserReposDao
    abstract fun commitsDao(): RoomCommitsDao

    companion object {
        const val DB_NAME = "github.db"
    }
}