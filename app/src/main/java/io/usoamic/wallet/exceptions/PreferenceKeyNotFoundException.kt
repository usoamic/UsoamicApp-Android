package io.usoamic.wallet.exceptions

import java.lang.Exception

class PreferenceKeyNotFoundException(key: String) : Exception("$key not found")