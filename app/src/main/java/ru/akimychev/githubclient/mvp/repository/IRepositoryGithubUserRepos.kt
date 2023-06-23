package ru.akimychev.githubclient.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos

interface IRepositoryGithubUserRepos {

    fun getRepos(user: GithubUser): Single<List<GithubUserRepos>>
}