package ru.akimychev.githubclient.mvp.model


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos

class RepositoryImpl(private val api: IDataSource) : Repository {

    override fun getUsers(): Single<List<GithubUser>> =
        api.getAllUsers().subscribeOn(Schedulers.io())

    override fun getRepos(reposUrl: String): Single<List<GithubUserRepos>> =
        api.getRepos(reposUrl).subscribeOn(Schedulers.io())

}