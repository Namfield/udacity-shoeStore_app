package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.welcome.WelcomeViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        // request the viewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // set the ViewModel for data binding
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        // set up the observer for the navigation to Welcome screen
        viewModel.navigateToWelcome.observe(viewLifecycleOwner,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    // navigate from welcome to instruction screen
                    findNavController().navigate(R.id.action_login_to_welcome)
                    // reset the navigation state
                    viewModel.doneNavigateToWelcome()
                }
            })

        return binding.root
    }
}