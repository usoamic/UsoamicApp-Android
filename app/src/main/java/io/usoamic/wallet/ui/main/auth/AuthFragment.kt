package io.usoamic.wallet.ui.main.auth

import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.domain.models.NavDirections
import io.usoamic.wallet.extensions.navigateTo
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
            addBtn.setOnClickListener {
                navigator.navigateTo(NavDirections.Add)
            }
            createBtn.setOnClickListener {
                navigator.navigateTo(NavDirections.Create)
            }
        }
    }

}
