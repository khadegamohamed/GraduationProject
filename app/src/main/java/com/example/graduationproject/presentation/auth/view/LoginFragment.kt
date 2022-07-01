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
import com.example.graduationproject.databinding.FragmentLoginBinding
import com.example.graduationproject.presentation.auth.model.User


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = LoginViewModel(requireActivity().applicationContext)
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
                       Log.d("Login", response.body().toString())
                       Log.d("Login", response.message().toString())
                       Log.d("Login", response.code().toString())
                    if(response.body()!!.isSuccessful) {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToBottomNavagation2()
                        )
                    }
                       Toast.makeText(activity,
                           response.body()!!.massage,Toast.LENGTH_SHORT).show()
                   }else{
                       Log.i("Login", response.message().toString())
                   }

                })


            }

        }

    }


}