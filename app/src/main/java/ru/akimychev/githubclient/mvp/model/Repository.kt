package ru.akimychev.githubclient.mvp.model

import io.reactivex.rxjava3.core.Observable

fun interface Repository {

    fun getUsers(): Observable<GithubUser>
}