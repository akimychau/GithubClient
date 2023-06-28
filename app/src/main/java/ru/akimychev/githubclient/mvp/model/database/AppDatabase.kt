package ru.akimychev.githubclient.mvp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.akimychev.githubclient.mvp.model.entity.RoomGithubUser
import ru.akimychev.githubclient.mvp.model.entity.RoomGithubUserRepos

@Database(
    entities = [RoomGithubUser::class, RoomGithubUserRepos::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomGithubUserDao
    abstract fun reposDao(): RoomGithubUserReposDao

    companion object {
        const val DB_NAME = "github.db"
    }
}