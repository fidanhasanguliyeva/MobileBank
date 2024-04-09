package com.mobilebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.mobilebank.databinding.ActivityMainBinding
import com.mobilebank.ui.login.LoginFragmentDirections
import com.mobilebank.utils.AnalyticsHelper
import com.mobilebank.utils.MainSharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AnalyticsHelper.initAnalytics(this)
        setUpNavigationController()
    }

    fun setUpNavigationController() {
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return

        navController = host.navController
        navGraph = navController.navInflater.inflate(R.navigation.main_navigation)

        navController.setGraph(navGraph, intent.extras)
        if (MainSharedPreferences(this, "MAIN").get(
                "isLoggedIn",
                false
            ) == true
        ) {
            navController.navigate(LoginFragmentDirections.actionGlobalHomeFragment())
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.isVisible = destination.id == R.id.homeFragment
        }
    }


}