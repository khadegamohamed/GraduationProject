package com.example.graduationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.databinding.FragmentLoginBinding

private lateinit var binding: FragmentLoginBinding
private lateinit var navController: NavController
class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(view)
        val viewroot = binding.root
       return viewroot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController  = findNavController()
        val action =LoginFragmentDirections.actionLoginFragmentToBottomNavagation2()
       binding.login.setOnClickListener{
           navController.navigate(action)
       }
    }

}