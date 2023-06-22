package ru.akimychev.githubclient.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.model.RepositoryImpl
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.presenter.list.IUserListPresenter
import ru.akimychev.githubclient.mvp.view.UsersView
import ru.akimychev.githubclient.mvp.view.list.IUserItemView
import ru.akimychev.githubclient.navigation.Screens

class UsersPresenter(
    private val repositoryImpl: RepositoryImpl,
    private val router: Router,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    private var disposable: Disposable? = null

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        viewState.init()

        usersListPresenter.itemClickListener = {
            router.navigateTo(Screens.details(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        disposable = repositoryImpl.getUsers().observeOn(uiScheduler)
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                Log.e("@@@", "Something went wrong")
            })
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}