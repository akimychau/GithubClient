package ru.akimychev.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.RoomGithubUser

class UserCacheImpl(private val db: AppDatabase) : IUserCache {

    override fun insertUsersToDb(githubUser: List<GithubUser>): Completable {
        return db.userDao().insertAll(githubUser.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.avatarUrl,
                user.reposUrl
            )
        })
    }

    override fun getUsersFromDb(): Single<List<GithubUser>> {
        return db.userDao().queryForAllUsers().map {
            it.map { roomUser ->
                GithubUser(
                    roomUser.id,
                    roomUser.login,
                    roomUser.avatarUrl,
                    roomUser.reposUrl
                )
            }
        }
    }
}