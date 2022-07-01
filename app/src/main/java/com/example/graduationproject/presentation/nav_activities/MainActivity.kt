package com.example.graduationproject.presentation.nav_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.graduationproject.R
import com.example.graduationproject.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment)as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigation= binding.bottomNavigationView
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.background = null
        bottomNavigation.menu.getItem(2).isEnabled = false
        NavigationUI.setupWithNavController(bottomNavigation, navController)
       navController.addOnDestinationChangedListener { controller, destination, arguments ->
           if (destination.id==R.id.loginFragment || destination.id ==R.id.splashscreenFragment){
               binding.bottomNavigationView.visibility = View.GONE
               binding.bottomAppBar.visibility = View.GONE
               binding.personFab.visibility = View.GONE
           }else{
               binding.bottomNavigationView.visibility = View.VISIBLE
               binding.bottomAppBar.visibility = View.VISIBLE
               binding.personFab.visibility = View.VISIBLE
           }
       }
    }
}