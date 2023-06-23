package ru.akimychev.githubclient.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.databinding.FragmentUsersBinding
import ru.akimychev.githubclient.mvp.model.api.ApiHolder
import ru.akimychev.githubclient.mvp.model.cache.UserCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.presenter.UsersPresenter
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserImpl
import ru.akimychev.githubclient.mvp.view.UsersView
import ru.akimychev.githubclient.navigation.BackPressedListener
import ru.akimychev.githubclient.ui.adapter.UsersRVAdapter
import ru.akimychev.githubclient.ui.image.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(), UsersView, BackPressedListener {

    private var _viewBinding: FragmentUsersBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            RepositoryGithubUserImpl(
                ApiHolder.api,
                App.networkStatus,
                UserCacheImpl(AppDatabase.getInstance())
            ),
            App.instance.router,
            AndroidSchedulers.mainThread()
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        _viewBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init() {
        viewBinding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        viewBinding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}