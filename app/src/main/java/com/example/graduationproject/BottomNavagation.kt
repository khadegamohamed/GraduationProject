package com.example.graduationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.graduationproject.databinding.ActivityBottomNavagationBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var navController: NavController
private lateinit var bottomNavagation: BottomNavigationView
private lateinit var binding:ActivityBottomNavagationBinding
class BottomNavagation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavagationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment2)as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavagation= binding.bottomNavagation
        NavigationUI.setupWithNavController(bottomNavagation, navController)

    }
}