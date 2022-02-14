package com.example.graduationproject.presentation.nav_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.graduationproject.R
import com.example.graduationproject.databinding.ActivityBottomNavagationBinding

import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomNavigation : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var binding:ActivityBottomNavagationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavagationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2)as NavHostFragment
         navController = navHostFragment.navController
        bottomNavigation= binding.bottomNavigationView
        bottomNavigation.background = null
        bottomNavigation.menu.getItem(2).isEnabled = false
        NavigationUI.setupWithNavController(bottomNavigation, navController)

    }
}