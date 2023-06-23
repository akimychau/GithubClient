package ru.akimychev.githubclient.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.GithubUser

interface IRepositoryGithubUser {

    fun getUsers(): Single<List<GithubUser>>
}