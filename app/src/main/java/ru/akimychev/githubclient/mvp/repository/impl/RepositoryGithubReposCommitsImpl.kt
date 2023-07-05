package ru.akimychev.githubclient.mvp.repository.impl

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.ICommitsCache
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubReposCommits

class RepositoryGithubReposCommitsImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICommitsCache
) : IRepositoryGithubReposCommits {

    override fun getCommits(repos: GithubUserRepos): Single<List<GithubCommits>> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                val some = repos.url + "/commits"
                api.getCommits(some).flatMap { commits ->
                    cache.insertCommitsToDb(commits, repos).andThen(Single.just(commits))
                }
            } else {
                cache.getCommitsFromDb(repos)
            }
        }.subscribeOn(Schedulers.io())
}