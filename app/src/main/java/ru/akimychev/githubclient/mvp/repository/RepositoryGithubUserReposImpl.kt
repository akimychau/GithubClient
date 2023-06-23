package ru.akimychev.githubclient.mvp.repository


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.IReposCache
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus

class RepositoryGithubUserReposImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IReposCache
) : IRepositoryGithubUserRepos {

    override fun getRepos(user: GithubUser): Single<List<GithubUserRepos>> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                user.reposUrl?.let { reposUrl ->
                    api.getRepos(reposUrl)
                        .flatMap { userRepos ->
                            cache.insertReposToDb(userRepos, user).andThen(Single.just(userRepos))
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                cache.getReposFromDb(user)
            }
        }.subscribeOn(Schedulers.io())
}
