package com.example.graduationproject.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.R
import com.example.graduationproject.domain.common.SharedPreferenceManager


class SplashscreenFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var sharedPref:SharedPreferenceManager
    private  var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         sharedPref = SharedPreferenceManager(this.requireContext())
        token = sharedPref.getToken()
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // if user has not logged gg to login
    if(token == null){
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(SplashscreenFragmentDirections
                    .actionSplashscreenFragmentToLoginFragment())
         },1000)

        }else{
            // if he has logged go to home directly
        findNavController().navigate(SplashscreenFragmentDirections
                             .actionSplashscreenFragmentToHomeFragment2())
    }

    }

}