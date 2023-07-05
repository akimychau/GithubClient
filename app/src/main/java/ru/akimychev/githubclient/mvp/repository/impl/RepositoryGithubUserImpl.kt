package ru.akimychev.githubclient.mvp.repository.impl


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.akimychev.githubclient.mvp.model.api.IDataSource
import ru.akimychev.githubclient.mvp.model.cache.IUserCache
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUser
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubUser

class RepositoryGithubUserImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUserCache
) : IRepositoryGithubUser {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.getAllUsers()
                    .flatMap { users ->
                        cache.insertUsersToDb(users).andThen(Single.just(users))
                    }
            } else {
                cache.getUsersFromDb()
            }
        }.subscribeOn(Schedulers.io())
}
