package ru.akimychev.githubclient.di.commits

import dagger.Subcomponent
import ru.akimychev.githubclient.di.commits.module.CommitsModule
import ru.akimychev.githubclient.mvp.presenter.CommitsPresenter

@CommitsScope
@Subcomponent(modules = [CommitsModule::class])
interface CommitsSubComponent {

    fun inject(commitsPresenter: CommitsPresenter)
}