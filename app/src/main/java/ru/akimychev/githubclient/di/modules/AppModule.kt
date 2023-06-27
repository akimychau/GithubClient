package ru.akimychev.githubclient.di.modules

import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}