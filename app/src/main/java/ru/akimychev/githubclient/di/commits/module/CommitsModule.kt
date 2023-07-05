package ru.akimychev.githubclient.di.commits.module

import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.di.commits.CommitsScope
import ru.akimychev.githubclient.di.commits.ICommitsScopeContainer
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.ICommitsCache
import ru.akimychev.githubclient.mvp.model.cache.impl.CommitsCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubReposCommits
import ru.akimychev.githubclient.mvp.repository.impl.RepositoryGithubReposCommitsImpl

@Module
class CommitsModule {

    @CommitsScope
    @Provides
    fun commitsCache(db: AppDatabase): ICommitsCache = CommitsCacheImpl(db)

    @CommitsScope
    @Provides
    fun commitsRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICommitsCache
    ): IRepositoryGithubReposCommits = RepositoryGithubReposCommitsImpl(api, networkStatus, cache)

    @CommitsScope
    @Provides
    fun scopeContainer(app: App): ICommitsScopeContainer = app
}