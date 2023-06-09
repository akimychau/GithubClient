package ru.akimychev.githubclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.databinding.FragmentUsersBinding
import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.navigation.BackPressedListener
import ru.akimychev.githubclient.presenter.UsersPresenter
import ru.akimychev.githubclient.view.UsersView

class UsersFragment : MvpAppCompatFragment(), UsersView, BackPressedListener {

    private var _viewBinding: FragmentUsersBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter { UsersPresenter(CountersModel(), App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        _viewBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            btnCounter1.setOnClickListener { presenter.counterClickBtnOne() }
            btnCounter2.setOnClickListener { presenter.counterClickBtnTwo() }
            btnCounter3.setOnClickListener { presenter.counterClickBtnThree() }
        }
    }

    override fun setBtnOneText(text: String) {
        viewBinding.btnCounter1.text = text
    }

    override fun setBtnTwoText(text: String) {
        viewBinding.btnCounter2.text = text
    }

    override fun setBtnThreeText(text: String) {
        viewBinding.btnCounter3.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onBackPressed() = presenter.onBackPressed()
}