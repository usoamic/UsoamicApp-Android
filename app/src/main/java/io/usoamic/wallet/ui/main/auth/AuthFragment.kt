package io.usoamic.wallet.ui.main.auth

import android.os.Bundle
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.FragmentAuthBinding
import io.usoamic.wallet.ui.base.BaseFragment

class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    override val viewModel: AuthViewModel by viewModels()

    private val binding: FragmentAuthBinding by lazy {
        FragmentAuthBinding.bind(requireView())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addBtn.setOnClickListener {
            println("!@onClicked")
        }
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}
