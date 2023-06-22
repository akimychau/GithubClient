package ru.akimychev.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.GithubUser

fun interface Repository {

    fun getUsers(): Single<List<GithubUser>>
}