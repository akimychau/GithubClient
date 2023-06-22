package ru.akimychev.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.databinding.FragmentForksCountBinding
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.mvp.presenter.ForksCountPresenter
import ru.akimychev.githubclient.mvp.view.ForksCountView
import ru.akimychev.githubclient.navigation.BackPressedListener

class ForksCountFragment : MvpAppCompatFragment(), ForksCountView, BackPressedListener {

    companion object {
        const val BUNDLE_FORKS_COUNT = "BUNDLE_GITHUB_USER"
        fun newInstance(repos: GithubUserRepos) = ForksCountFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_FORKS_COUNT, repos)
            }
        }
    }

    private var _viewBinding: FragmentForksCountBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter: ForksCountPresenter by moxyPresenter { ForksCountPresenter(App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentForksCountBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<GithubUserRepos>(BUNDLE_FORKS_COUNT)
            ?.let { presenter.show(it) }
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun showNumberOfForks(forks: String) {
        viewBinding.forksCount.text = forks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}