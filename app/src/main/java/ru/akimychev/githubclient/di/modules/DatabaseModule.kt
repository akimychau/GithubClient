package ru.akimychev.githubclient.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()
}