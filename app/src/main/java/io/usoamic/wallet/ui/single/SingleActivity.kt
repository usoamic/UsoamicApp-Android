package io.usoamic.wallet.ui.single

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.realm.Realm
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.ActivitySingleBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import javax.inject.Inject

class SingleActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SingleViewModel>
    private val viewModel: SingleViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding(ActivitySingleBinding::bind, R.id.container)

    private lateinit var errorDialog: AlertDialog
    private val navigator: NavController by lazy {
        binding.hostFragment.findNavController()
    }

    private fun inject() {
        UsoamicWallet.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        Realm.init(this)
        initObservers()
    }

    private fun initObservers() {
        observe(viewModel.leLocked) {
            navigator.navigate(R.id.unlockFragment)
        }
    }

    override fun onDestroy() {
        if(::errorDialog.isInitialized) {
            errorDialog.dismiss()
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        initObservers()
        viewModel.checkThatNeedLock()
    }
}
