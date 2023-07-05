package ru.akimychev.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos

interface IReposCache {

    fun insertReposToDb(githubUserRepos: List<GithubUserRepos>, githubUser: GithubUser): Completable
    fun getReposFromDb(user: GithubUser): Single<List<GithubUserRepos>>
}