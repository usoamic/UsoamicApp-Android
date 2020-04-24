package io.usoamic.wallet.ui.main.auth

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.domain.models.AppArguments
import io.usoamic.wallet.extensions.navigateTo
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject


class AuthFragment : BaseViewModelFragment(R.layout.fragment_auth) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AuthViewModel>
    override val viewModel: AuthViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentAuthBinding

    override fun inject() {
        UsoamicWallet.component.authSubcomponent.inject(this)
    }

    override fun initBinding() {
        binding = FragmentAuthBinding.bind(requireView())
    }

    override fun initListeners() {
        binding.addBtn.setOnClickListener {
            navigator.navigateTo(AppArguments.Add("pk"))
        }
    }

}
