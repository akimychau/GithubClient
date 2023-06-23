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
import ru.akimychev.githubclient.databinding.FragmentReposBinding
import ru.akimychev.githubclient.mvp.model.api.ApiHolder
import ru.akimychev.githubclient.mvp.model.cache.ReposCacheImpl
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.presenter.ReposPresenter
import ru.akimychev.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import ru.akimychev.githubclient.mvp.view.ReposView
import ru.akimychev.githubclient.navigation.BackPressedListener
import ru.akimychev.githubclient.ui.adapter.ReposRVAdapter
import ru.akimychev.githubclient.ui.image.GlideImageLoader

class ReposFragment : MvpAppCompatFragment(), ReposView, BackPressedListener {

    private var _viewBinding: FragmentReposBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable(BUNDLE_GITHUB_USER) as GithubUser?
        ReposPresenter(
            user,
            App.instance.router,
            RepositoryGithubUserReposImpl(
                ApiHolder.api,
                App.networkStatus,
                ReposCacheImpl(AppDatabase.getInstance())
            ),
            AndroidSchedulers.mainThread()
        )
    }

    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        _viewBinding = FragmentReposBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init(user: GithubUser) {
        user.avatarUrl?.let { GlideImageLoader().loadInto(it, viewBinding.userAvatar) }
        viewBinding.userLogin.text = user.login
        viewBinding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        viewBinding.rvRepos.adapter = adapter
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
        const val BUNDLE_GITHUB_USER = "BUNDLE_GITHUB_USER"
        fun newInstance(user: GithubUser) = ReposFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_GITHUB_USER, user)
            }
        }
    }
}