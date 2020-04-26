package io.usoamic.wallet.ui.main.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentCreateBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import io.usoamic.wallet.ui.main.add.AddViewModel
import javax.inject.Inject

class CreateFragment : BaseViewModelFragment(R.layout.fragment_create) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddViewModel>
    override val viewModel: AddViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentCreateBinding

    override fun inject() {
        UsoamicWallet.component.createSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun initBinding() {
        binding = FragmentCreateBinding.bind(requireView())
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}