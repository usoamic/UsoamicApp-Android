package io.usoamic.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.usoamic.wallet.ui.main.AuthFragment

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AuthFragment.newInstance())
                    .commitNow()
        }
    }
}
