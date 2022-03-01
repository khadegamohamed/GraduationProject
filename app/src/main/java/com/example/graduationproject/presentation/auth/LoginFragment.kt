package com.example.graduationproject.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                       savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = LoginViewModel()
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userNameEt = binding.usernameEt
        var userCodeEt = binding.userCodeEt
              binding.loginBtn.setOnClickListener {
                var valid = loginViewModel.signIn(userNameEt,userCodeEt)
                 if(valid) {
                      findNavController().navigate(
                          LoginFragmentDirections
                              .actionLoginFragmentToBottomNavagation2()
                      )
                  }

              }

    }



}