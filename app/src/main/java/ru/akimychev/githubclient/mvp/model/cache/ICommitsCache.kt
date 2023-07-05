package ru.akimychev.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos

interface ICommitsCache {

    fun insertCommitsToDb(
        githubCommits: List<GithubCommits>,
        githubUserRepos: GithubUserRepos
    ): Completable

    fun getCommitsFromDb(repos: GithubUserRepos): Single<List<GithubCommits>>
}