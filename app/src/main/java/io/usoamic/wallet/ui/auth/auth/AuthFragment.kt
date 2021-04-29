package io.usoamic.wallet.ui.auth.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.extensions.setVersion
import io.usoamic.wallet.ui.base.BaseFragment
import io.usoamic.wallet.utils.BuildConfigHelper


class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    override val binding: FragmentAuthBinding by viewBinding {
        FragmentAuthBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.authSubcomponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVersion()
    }

    override fun initListeners() = with(binding) {
        addBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.addFragment)
        )
        createBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.createFragment)
        )
    }
}
