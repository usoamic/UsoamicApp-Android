package io.usoamic.wallet.ui.auth.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAddBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class AddFragment : BaseViewModelFragment(R.layout.fragment_add) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddViewModel>
    override val viewModel: AddViewModel by viewModels { viewModelFactory }

    override val binding: FragmentAddBinding by viewBinding {
        FragmentAddBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.addSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leAccountAdd) {
            goToUnlock()
        }
    }

    override fun initListeners() = with(binding) {
        btnAdd.setOnClickListener {
            viewModel.onAddClick(etPrivateKey.value, etPassword.value, etConfirmPassword.value)
        }
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    private fun goToUnlock() {
        navigator.navigate(R.id.action_addFragment_to_unlockFragment)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}