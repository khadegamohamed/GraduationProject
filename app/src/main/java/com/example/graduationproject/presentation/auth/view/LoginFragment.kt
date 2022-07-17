package com.example.graduationproject.presentation.auth.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.databinding.FragmentLoginBinding
import com.example.graduationproject.presentation.auth.model.User

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPrefManager:SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = LoginViewModel(requireActivity().applicationContext)
        sharedPrefManager = SharedPreferenceManager(this.requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userNameEt = binding.usernameEt
        var userCodeEt = binding.userCodeEt

        binding.loginBtn.setOnClickListener {
            var valid = loginViewModel.signIn(userNameEt, userCodeEt)
            if (valid) {
                // post user data to the server
                val user = User(userNameEt.text.toString(),
                    userCodeEt.text.toString())

                loginViewModel.login(user)
                loginViewModel.loginResponse.observe(viewLifecycleOwner
                                            , Observer { response ->

                   if(response.isSuccessful){
                    if(response.body()!= null) {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment2() )


                       val tokenVal = response.body()!!.token
                        //save token to sharedPreference
                        sharedPrefManager.saveToken(tokenVal)
                       val userRole= response.body()!!.role
                        //save role to sharedPreference
                        sharedPrefManager.saveUserRole(userRole)
                        Log.i("Login", response.body().toString())
                    }
                       Toast.makeText(activity,
                           response.body()!!.massage,Toast.LENGTH_SHORT).show()
                   }else{
                       Log.i("Login", response.message().toString())
                   }

                })

            }
            // temp
          // findNavController().navigate(LoginFragmentDirections
           //     .actionLoginFragmentToHomeFragment2())
        }

    }



}