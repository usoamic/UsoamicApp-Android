package io.usoamic.wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        Realm.init(this)
    }
}
