package ru.akimychev.githubclient.mvp.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubCommits

@Dao
interface RoomCommitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commit: RoomGithubCommits): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(commits: List<RoomGithubCommits>): Completable

    @Delete
    fun delete(commit: RoomGithubCommits): Completable

    @Query("SELECT * FROM commits")
    fun getAll(): Single<List<RoomGithubCommits>>

    @Query("SELECT * FROM commits WHERE reposId = :reposId")
    fun findForUser(reposId: String): Single<List<RoomGithubCommits>>
}