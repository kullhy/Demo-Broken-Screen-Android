package com.hidenobi.baseapplication.other

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hidenobi.baseapplication.other.PreferenceHelper.codeLanguage
import java.util.Locale

// region context extension
fun Context.setUpLanguage(): Context {
    val config = resources.configuration
    codeLanguage?.let {
        val locale = Locale(it)
        config.setLocale(locale)
    }
    return createConfigurationContext(config)
}

fun Context.restartApplication(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    this.startActivity(intent)
}
// endregion context extension

// region fragment extension
fun Fragment.closeFragment() {
    findNavController().popBackStack()
}

// endregion fragment extension