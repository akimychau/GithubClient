package ru.akimychev.githubclient.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.databinding.FragmentCommitsBinding
import ru.akimychev.githubclient.mvp.model.entity.api.GithubUserRepos
import ru.akimychev.githubclient.mvp.presenter.CommitsPresenter
import ru.akimychev.githubclient.mvp.view.CommitsView
import ru.akimychev.githubclient.navigation.BackPressedListener
import ru.akimychev.githubclient.ui.adapter.CommitsRVAdapter

class CommitsFragment : MvpAppCompatFragment(), CommitsView, BackPressedListener {

    private var _viewBinding: FragmentCommitsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var adapter: CommitsRVAdapter? = null

    private val presenter: CommitsPresenter by moxyPresenter {
        val repos = arguments?.getParcelable<GithubUserRepos>(BUNDLE_REPOS) as GithubUserRepos
        CommitsPresenter(repos).apply {
            App.instance.initCommitsSubComponent()?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentCommitsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init() {
        viewBinding.rvCommits.layoutManager = LinearLayoutManager(context)
        adapter = CommitsRVAdapter(presenter.commitsListPresenter)
        viewBinding.rvCommits.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun browse(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        const val BUNDLE_REPOS = "BUNDLE_REPOS"
        fun newInstance(repos: GithubUserRepos) = CommitsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_REPOS, repos)
            }
        }
    }
}