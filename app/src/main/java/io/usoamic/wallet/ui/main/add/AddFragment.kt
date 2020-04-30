package io.usoamic.wallet.ui.main.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAddBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class AddFragment : BaseViewModelFragment(R.layout.fragment_add) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddViewModel>
    override val viewModel: AddViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentAddBinding

    override fun inject() {
        UsoamicWallet.component.addSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun initBinding() {
        binding = FragmentAddBinding.bind(requireView())
    }

    override fun initListeners() {
        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.onAddClick(etPrivateKey.value, etPassword.value, etConfirmPassword.value)
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}