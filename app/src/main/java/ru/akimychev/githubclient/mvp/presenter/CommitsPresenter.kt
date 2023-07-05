package ru.akimychev.githubclient.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.akimychev.githubclient.di.commits.ICommitsScopeContainer
import ru.akimychev.githubclient.mvp.model.entity.api.GithubCommits
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos
import ru.akimychev.githubclient.mvp.presenter.list.ICommitsListPresenter
import ru.akimychev.githubclient.mvp.repository.IRepositoryGithubReposCommits
import ru.akimychev.githubclient.mvp.view.CommitsView
import ru.akimychev.githubclient.mvp.view.list.ICommitsItemView
import ru.akimychev.githubclient.utils.disposeBy
import javax.inject.Inject

class CommitsPresenter(private val repos: GithubUserRepos) : MvpPresenter<CommitsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryGithubReposCommitsImpl: IRepositoryGithubReposCommits

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var commitsScopeContainer: ICommitsScopeContainer

    private var disposable = CompositeDisposable()

    class CommitsListPresenter : ICommitsListPresenter {

        val commits = mutableListOf<GithubCommits>()

        override var itemClickListener: ((ICommitsItemView) -> Unit)? = null

        override fun getCount() = commits.size

        override fun bindView(view: ICommitsItemView) {
            val commit = commits[view.pos]
            view.setName(commit.commit.message)
        }
    }

    val commitsListPresenter = CommitsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        repos.let { viewState.init() }

        commitsListPresenter.itemClickListener = {
            viewState.browse(commitsListPresenter.commits[it.pos].htmlUrl)
        }
    }

    private fun loadData() {

        repos.let {
            repositoryGithubReposCommitsImpl.getCommits(it)
                .observeOn(uiScheduler)
                .subscribe({ commits ->
                    commitsListPresenter.commits.addAll(commits)
                    viewState.updateList()
                }, {error->
                    Log.e("@@@", "Commits Something went wrong\n${error.message}")
                }).disposeBy(disposable)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        commitsScopeContainer.releaseCommitsSubComponent()
        super.onDestroy()
        disposable.dispose()
    }
}