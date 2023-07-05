package ru.akimychev.githubclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos


interface IDataSource {

    @GET("/users")
    fun getAllUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url reposUrl: String): Single<List<GithubUserRepos>>

    @GET
    fun getCommits(@Url commitUrl: String): Single<List<GithubCommits>>
}