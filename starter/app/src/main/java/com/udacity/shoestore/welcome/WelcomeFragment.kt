package com.udacity.shoestore.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // variable for the ViewModel
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        // request the viewModel
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // set the ViewModel for data binding
        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this

        // observe the LiveData indicating the navigation from Welcome to Instruction
        viewModel.navigateToInstruction.observe(viewLifecycleOwner,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    // navigate from welcome to instruction screen
                    findNavController().navigate(R.id.action_welcome_to_instruction)
                    // reset the navigation state
                    viewModel.doneNavigateToInstruction()
                }
            })

        return binding.root
    }
}
