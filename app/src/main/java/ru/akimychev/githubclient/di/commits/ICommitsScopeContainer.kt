package ru.akimychev.githubclient.di.commits

interface ICommitsScopeContainer {

    fun initCommitsSubComponent(): CommitsSubComponent?

    fun releaseCommitsSubComponent()
}