package io.usoamic.wallet.ui.main.auth

import androidx.navigation.Navigation
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.ui.base.BaseFragment


class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    private lateinit var binding: FragmentAuthBinding

    override fun inject() {
        UsoamicWallet.component.authSubcomponent.inject(this)
    }

    override fun initBinding() {
        binding = FragmentAuthBinding.bind(requireView())
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
