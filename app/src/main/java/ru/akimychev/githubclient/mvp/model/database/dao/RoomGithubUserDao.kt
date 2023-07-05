package ru.akimychev.githubclient.mvp.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.akimychev.githubclient.mvp.model.entity.database.RoomGithubUser

@Dao
interface RoomGithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDb: RoomGithubUser): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersDb: List<RoomGithubUser>): Completable

    @Delete
    fun delete(userDb: RoomGithubUser): Completable

    @Query("SELECT * FROM users")
    fun queryForAllUsers(): Single<List<RoomGithubUser>>

    @Query("SELECT * FROM users WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): Single<RoomGithubUser>
}