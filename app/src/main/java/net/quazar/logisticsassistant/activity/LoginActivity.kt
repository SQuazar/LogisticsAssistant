package net.quazar.logisticsassistant.activity

import androidx.fragment.app.FragmentActivity
import net.quazar.logisticsassistant.R
import net.quazar.logisticsassistant.ui.login.LoginFragment
import net.quazar.logisticsassistant.ui.login.RequestCodeFragment


class LoginActivity : FragmentActivity(R.layout.login_layout) {

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.loginFragmentContainer)
        if (currentFragment is RequestCodeFragment) {
            val phone = currentFragment.phoneNumber
            currentFragment.cancelTimer()
            supportFragmentManager.beginTransaction()
                .replace(R.id.loginFragmentContainer, LoginFragment.newInstance(phone))
                .commit()
            return
        }
        finishAffinity()
    }
}