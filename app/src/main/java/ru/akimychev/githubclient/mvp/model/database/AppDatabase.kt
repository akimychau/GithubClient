package ru.akimychev.githubclient.mvp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
        private var instance: AppDatabase? = null

        fun getInstance() = instance ?: throw RuntimeException("Database wasn't created")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            }
        }
    }
}