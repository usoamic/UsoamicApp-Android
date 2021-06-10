package io.usoamic.app.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentStartBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.setVersion
import io.usoamic.app.ui.base.BaseViewModelFragment
import javax.inject.Inject

class StartFragment : BaseViewModelFragment(R.layout.fragment_start) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StartViewModel>
    override val viewModel: StartViewModel by viewModels { viewModelFactory }

    override val binding: FragmentStartBinding by viewBinding {
        FragmentStartBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVersion()
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldHasAccount, ::goToNextFragment)
    }

    private fun goToNextFragment(hasAccount: Boolean) {
        val direction = if (hasAccount) {
            R.id.unlockFragment
        } else {
            R.id.authFragment
        }
        navigator.navigate(direction)
    }
}