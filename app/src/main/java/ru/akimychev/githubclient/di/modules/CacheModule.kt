package ru.akimychev.githubclient.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.mvp.model.cache.IReposCache
import ru.akimychev.githubclient.mvp.model.cache.IUserCache
import ru.akimychev.githubclient.mvp.model.cache.ReposCacheImpl
import ru.akimychev.githubclient.mvp.model.cache.UserCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun usersCache(db: AppDatabase): IUserCache = UserCacheImpl(db)

    @Singleton
    @Provides
    fun reposCache(db: AppDatabase): IReposCache = ReposCacheImpl(db)
}