package com.pop_flake.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pop_flake.R
import com.pop_flake.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        navController = NavController(applicationContext)
//        Navigation.findNavController(this, R.id.nav_host)
////        navController
//            .addOnDestinationChangedListener { controller, destination, arguments ->
//                when (destination.id) {
//                    R.id.splashFragment, R.id.movieDetailFragment -> binding.bottomNav.visibility =
//                        View.GONE
//                    else -> binding.bottomNav.visibility = View.VISIBLE
//                }
//            }

        initView()

//        binding.bottomNav.setOnNavigationItemSelectedListener {
//            when (item.itemId) {
//                R.id.home_menu -> navController.navigate(R.id.homeFragment)
//                R.id.search_menu -> R.id.homeFragment
//                R.id.setting_menu -> navController.navigate(R.id.settingFragment)
//                else -> Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
//            }
//        }
//        binding.bottomNav.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
////                R.id.setting_menu -> R.id.homeFragment
//                R.id.settingFragment -> navController.navigate(R.id.settingFragment)
//                else -> Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
//            }
//            true
//        }
    }

    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        binding.bottomNav.setupWithNavController(navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.splashFragment, R.id.movieDetailFragment -> binding.bottomNav.visibility =
                    View.GONE
                else -> binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }
}