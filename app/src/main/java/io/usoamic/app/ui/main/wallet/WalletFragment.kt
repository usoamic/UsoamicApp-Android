package io.usoamic.app.ui.main.wallet

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.databinding.FragmentWalletBinding
import io.usoamic.app.ui.base.BaseFragment

class WalletFragment : BaseFragment(R.layout.fragment_wallet) {
    override val binding: FragmentWalletBinding by viewBinding {
        FragmentWalletBinding.bind(it.requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }
}