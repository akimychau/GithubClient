package ru.akimychev.githubclient.di.repos.module

import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.di.repos.IReposScopeContainer
import ru.akimychev.githubclient.di.repos.ReposScope
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.IReposCache
import ru.akimychev.githubclient.mvp.model.cache.ReposCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubUserRepos
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserReposImpl

@Module
class ReposModule {

    @ReposScope
    @Provides
    fun reposCache(db: AppDatabase): IReposCache = ReposCacheImpl(db)

    @ReposScope
    @Provides
    fun userDetailsRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache,
    ): IRepositoryGithubUserRepos = RepositoryGithubUserReposImpl(api, networkStatus, cache)

    @ReposScope
    @Provides
    open fun scopeContainer(app: App): IReposScopeContainer = app
}