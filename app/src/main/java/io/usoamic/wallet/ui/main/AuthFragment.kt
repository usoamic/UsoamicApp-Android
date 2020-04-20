package io.usoamic.wallet.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import io.usoamic.wallet.ui.base.BaseFragment
import io.usoamic.wallet.R

class AuthFragment : BaseFragment(R.layout.auth_fragment) {

    companion object {
        fun newInstance() = AuthFragment()
    }

    private lateinit var viewModel: AuthViewModel



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
