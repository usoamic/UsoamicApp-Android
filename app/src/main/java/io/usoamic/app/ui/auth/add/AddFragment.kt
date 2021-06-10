package io.usoamic.app.ui.auth.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentAddBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.setVersion
import io.usoamic.app.extensions.value
import io.usoamic.app.ui.base.BaseViewModelFragment
import javax.inject.Inject

class AddFragment : BaseViewModelFragment(R.layout.fragment_add) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddViewModel>
    override val viewModel: AddViewModel by viewModels { viewModelFactory }

    override val binding: FragmentAddBinding by viewBinding {
        FragmentAddBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.addSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setVersion()
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