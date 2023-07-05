package ru.akimychev.githubclient.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos

interface IRepositoryGithubReposCommits {

    fun getCommits(repos: GithubUserRepos): Single<List<GithubCommits>>
}