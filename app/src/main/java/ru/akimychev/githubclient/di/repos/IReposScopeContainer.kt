package ru.akimychev.githubclient.di.repos

interface IReposScopeContainer {

    fun initReposSubComponent(): ReposSubComponent?

    fun releaseReposSubComponent()
}