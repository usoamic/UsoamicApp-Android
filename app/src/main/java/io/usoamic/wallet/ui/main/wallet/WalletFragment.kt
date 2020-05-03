package io.usoamic.wallet.ui.main.wallet

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.FragmentWalletBinding
import io.usoamic.wallet.ui.base.BaseFragment

class WalletFragment : BaseFragment(R.layout.fragment_wallet) {
    private lateinit var binding: FragmentWalletBinding

    override fun initBinding() {
        binding = FragmentWalletBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }
}