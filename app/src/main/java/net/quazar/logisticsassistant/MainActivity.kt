package net.quazar.logisticsassistant

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import net.quazar.logisticsassistant.activity.LoginActivity
import net.quazar.logisticsassistant.service.LoginService
import net.quazar.logisticsassistant.service.LoginServiceTestImpl
import net.quazar.logisticsassistant.ui.login.LoginFragment
import kotlin.system.exitProcess

class MainActivity : FragmentActivity(R.layout.main_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setIcon(R.drawable.logo_action_bar)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        findViewById<TextView>(R.id.pageTitle).text = "Задания"
        supportFragmentManager.beginTransaction()
            .replace(R.id.pageFragment, TasksFragment())
            .commitNow()

        val nav = findViewById<BottomNavigationView>(R.id.mainNavigationBar)
        nav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.tasks_page -> {
                    findViewById<TextView>(R.id.pageTitle).text = "Задания"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.pageFragment, TasksFragment())
                        .commitNow()
                    true
                }
                else -> false
            }
        }
        nav.setOnItemReselectedListener { false }
//        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        exitProcess(1)
    }

    companion object {
        private val loginService: LoginService = LoginServiceTestImpl()

        @JvmStatic
        fun getLoginService() : LoginService {
            return loginService
        }
    }
}