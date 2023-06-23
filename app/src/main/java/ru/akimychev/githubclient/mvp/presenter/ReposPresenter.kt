package ru.akimychev.githubclient.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.mvp.presenter.list.IReposListPresenter
import ru.akimychev.githubclient.mvp.view.ReposView
import ru.akimychev.githubclient.mvp.view.list.IReposItemView
import ru.akimychev.githubclient.navigation.Screens
import ru.akimychev.githubclient.utils.disposeBy

class ReposPresenter(
    private val user: GithubUser?,
    private val router: Router,
    private val repositoryGithubUserReposImpl: RepositoryGithubUserReposImpl,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<ReposView>() {

    private var bag = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {

        val repos = mutableListOf<GithubUserRepos>()

        override var itemClickListener: ((IReposItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: IReposItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        user?.let { viewState.init(it) }

        reposListPresenter.itemClickListener = {
            router.navigateTo(Screens.forks(reposListPresenter.repos[it.pos]))
        }
    }

    private fun loadData() {

        user?.let { user ->
            repositoryGithubUserReposImpl.getRepos(user)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    Log.e("@@@", "Repo Something went wrong")
                }).disposeBy(bag)
        }
    }


    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}