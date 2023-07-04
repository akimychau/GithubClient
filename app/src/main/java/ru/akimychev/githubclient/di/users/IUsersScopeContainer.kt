package ru.akimychev.githubclient.di.users

interface IUsersScopeContainer {

    fun initUserSubComponent(): UsersSubComponent

    fun releaseUserSubComponent()
}