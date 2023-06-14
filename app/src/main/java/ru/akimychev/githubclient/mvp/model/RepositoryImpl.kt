package ru.akimychev.githubclient.mvp.model

import io.reactivex.rxjava3.core.Observable

class RepositoryImpl : Repository {

    private val users = listOf(
        GithubUser("Cat"),
        GithubUser("Dog"),
        GithubUser("Mouse"),
        GithubUser("Pig"),
        GithubUser("Rat")
    )

    override fun getUsers(): Observable<GithubUser> = Observable.fromIterable(users)
}