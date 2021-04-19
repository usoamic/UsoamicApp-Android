package io.usoamic.wallet.ui.auth.create

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.ethereum.AccountCredentials
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.custom.listview.model.ListItem
import io.usoamic.wallet.databinding.FragmentCreateBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.copyToClipboard
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class CreateFragment : BaseViewModelFragment(R.layout.fragment_create) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CreateViewModel>
    override val viewModel: CreateViewModel by viewModels { viewModelFactory }

    override val binding: FragmentCreateBinding by viewBinding {
        FragmentCreateBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.createSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldContent, ::setAccountCredentials)
    }

    override fun initListeners() {
        super.initListeners()
        binding.btnNext.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_createFragment_to_addFragment)
        )
    }

    private fun setAccountCredentials(data: AccountCredentials) {
        binding.listView.apply {
            addAll(
                listOf(
                    ListItem(getString(R.string.address), data.address),
                    ListItem(getString(R.string.private_key), data.privateKey)
                )
            )
            setOnItemClickListener { _, item ->
                copyToClipboard(item.subtitle)
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}