package ru.akimychev.githubclient.mvp.model.cache.impl

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.cache.ICommitsCache
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.entity.api.CommitsMessage
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubCommits

class CommitsCacheImpl(private val db: AppDatabase) : ICommitsCache {

    override fun insertCommitsToDb(
        githubCommits: List<GithubCommits>,
        githubUserRepos: GithubUserRepos
    ): Completable {
        return db.commitsDao().insertAll(githubCommits.map { commits ->
            RoomGithubCommits(
                commits.sha,
                commits.commit.message,
                commits.htmlUrl,
                githubUserRepos.id
            )
        })
    }

    override fun getCommitsFromDb(repos: GithubUserRepos): Single<List<GithubCommits>> {
        return db.commitsDao().findForUser(repos.id).map {
            it.map { roomCommits ->
                GithubCommits(
                    roomCommits.sha,
                    CommitsMessage(roomCommits.commit),
                    roomCommits.htmlUrl
                )
            }
        }
    }
}