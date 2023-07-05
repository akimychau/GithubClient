package ru.akimychev.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUser

interface IUserCache {

    fun insertUsersToDb(githubUser: List<GithubUser>): Completable
    fun getUsersFromDb(): Single<List<GithubUser>>
}