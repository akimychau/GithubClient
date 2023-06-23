package ru.akimychev.githubclient.mvp.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.RoomGithubUserRepos

@Dao
interface RoomGithubUserReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUserRepos): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<RoomGithubUserRepos>): Completable

    @Delete
    fun delete(user: RoomGithubUserRepos): Completable

    @Query("SELECT * FROM repos")
    fun getAll(): Single<List<RoomGithubUserRepos>>

    @Query("SELECT * FROM repos WHERE userId = :userId")
    fun findForUser(userId: String): Single<List<RoomGithubUserRepos>>
}