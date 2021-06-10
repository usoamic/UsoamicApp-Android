package io.usoamic.app.ui.auth.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.usecases.ethereum.AccountCredentialsModel
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.custom.listview.model.ListItem
import io.usoamic.app.databinding.FragmentCreateBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.copyToClipboard
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.setVersion
import io.usoamic.app.ui.base.BaseViewModelFragment
import javax.inject.Inject

class CreateFragment : BaseViewModelFragment(R.layout.fragment_create) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CreateViewModel>
    override val viewModel: CreateViewModel by viewModels { viewModelFactory }

    override val binding: FragmentCreateBinding by viewBinding {
        FragmentCreateBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.createSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setVersion()
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

    private fun setAccountCredentials(data: AccountCredentialsModel) {
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