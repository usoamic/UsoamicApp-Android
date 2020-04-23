package io.usoamic.wallet.ui.main.auth

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.extensions.navigateTo
import io.usoamic.wallet.models.AppArguments
import io.usoamic.wallet.ui.base.BaseFragment


class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    override val viewModel: AuthViewModel by viewModels()

    private lateinit var binding: FragmentAuthBinding

    override fun initBinding() {
        binding = FragmentAuthBinding.bind(requireView())
    }

    override fun initListeners() {
        binding.addBtn.setOnClickListener {
            navigator.navigateTo(AppArguments.Add("pk"))
        }
    }

}
