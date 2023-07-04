package ru.akimychev.githubclient.di.users.module

import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.di.users.IUsersScopeContainer
import ru.akimychev.githubclient.di.users.UsersScope
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.IUserCache
import ru.akimychev.githubclient.mvp.model.cache.UserCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubUser
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserImpl

@Module
class UsersModule {

    @UsersScope
    @Provides
    fun usersCache(db: AppDatabase): IUserCache = UserCacheImpl(db)

    @UsersScope
    @Provides
    fun userListRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache,
    ): IRepositoryGithubUser = RepositoryGithubUserImpl(api, networkStatus, cache)

    @UsersScope
    @Provides
    open fun scopeContainer(app: App): IUsersScopeContainer = app
}