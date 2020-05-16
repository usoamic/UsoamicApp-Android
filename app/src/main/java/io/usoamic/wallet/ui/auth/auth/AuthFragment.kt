package io.usoamic.wallet.ui.auth.auth

import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.ui.base.BaseFragment


class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    override val binding: FragmentAuthBinding by viewBinding {
        FragmentAuthBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.authSubcomponent.inject(this)
    }

    override fun initListeners() {
        binding.apply {
            addBtn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.addFragment)
            )
            createBtn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.createFragment)
            )
        }
    }

}
