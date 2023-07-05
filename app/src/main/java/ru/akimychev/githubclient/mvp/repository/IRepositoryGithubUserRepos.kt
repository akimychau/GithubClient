package ru.akimychev.githubclient.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos

interface IRepositoryGithubUserRepos {

    fun getRepos(user: GithubUser): Single<List<GithubUserRepos>>
}