package ru.akimychev.githubclient.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.akimychev.githubclient.App
import ru.akimychev.githubclient.R
import ru.akimychev.githubclient.databinding.ActivityMainBinding
import ru.akimychev.githubclient.navigation.BackPressedListener
import ru.akimychev.githubclient.mvp.presenter.MainPresenter
import ru.akimychev.githubclient.mvp.view.MainView
import java.util.Observable

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private var viewBinding: ActivityMainBinding? = null

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is BackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}