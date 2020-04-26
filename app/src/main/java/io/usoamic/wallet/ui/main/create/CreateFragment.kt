package io.usoamic.wallet.ui.main.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.custom.listview.model.ListItem
import io.usoamic.wallet.databinding.FragmentCreateBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.domain.models.create.AccountData
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class CreateFragment : BaseViewModelFragment(R.layout.fragment_create) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CreateViewModel>
    override val viewModel: CreateViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentCreateBinding

    override fun inject() {
        UsoamicWallet.component.createSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun initObservers() {
        observe(viewModel.ldContent, ::setAccountData)
    }

    private fun setAccountData(data: AccountData) {
        binding.listView.addAll(listOf(
            ListItem(getString(R.string.address), data.address),
            ListItem(getString(R.string.private_key), data.privateKey)
        ))
    }

    override fun initBinding() {
        binding = FragmentCreateBinding.bind(requireView())
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}