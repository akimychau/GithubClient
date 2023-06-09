package ru.akimychev.githubclient

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.databinding.ActivityMainBinding
import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.presenter.MainPresenter
import ru.akimychev.githubclient.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private var viewBinding: ActivityMainBinding? = null

    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        viewBinding?.btnCounter1?.setOnClickListener { presenter.counterClickBtnOne() }
        viewBinding?.btnCounter2?.setOnClickListener { presenter.counterClickBtnTwo() }
        viewBinding?.btnCounter3?.setOnClickListener { presenter.counterClickBtnThree() }
    }

    override fun setBtnOneText(text: String) {
        viewBinding?.btnCounter1?.text = text
    }

    override fun setBtnTwoText(text: String) {
        viewBinding?.btnCounter2?.text = text
    }

    override fun setBtnThreeText(text: String) {
        viewBinding?.btnCounter3?.text = text
    }
}