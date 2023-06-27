package ru.akimychev.githubclient.di.modules

import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.IReposCache
import ru.akimychev.githubclient.mvp.model.cache.IUserCache
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubUser
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubUserRepos
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserImpl
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun userListRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache,
    ): IRepositoryGithubUser = RepositoryGithubUserImpl(api, networkStatus, cache)

    @Singleton
    @Provides
    fun userDetailsRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache,
    ): IRepositoryGithubUserRepos = RepositoryGithubUserReposImpl(api, networkStatus, cache)
}