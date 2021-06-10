package io.usoamic.app.ui.auth.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentAuthBinding
import io.usoamic.app.extensions.setVersion
import io.usoamic.app.ui.base.BaseFragment


class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    override val binding: FragmentAuthBinding by viewBinding {
        FragmentAuthBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.authSubcomponent.inject(this)
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
